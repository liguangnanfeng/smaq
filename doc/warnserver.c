/*************************************************  
* File name   : server.c  
* Description : 单进程并发服务器  
* Author      : 84698281@qq.com  
* Version     : V1.0  
* Date        : 2017-11-13 
*************************************************/   
#include <stdio.h>    
#include <string.h>    
#include <unistd.h>    
#include <sys/types.h>    
#include <sys/socket.h>    
#include <netinet/in.h>    
#include <arpa/inet.h>    
//#include <sys/time.h>  
#include <time.h>  
#include <stdlib.h>    
#include <mysql/mysql.h>
#include <netdb.h>
#include <string.h>


#define DBG  1                //显示调试信息开关
#define PORT 6666               //服务器端口    
#define BACKLOG 5               //listen队列中等待的连接数    
#define MAXDATASIZE 1024        //缓冲区大小  
#define DEVSTR_LEN   5           //设备标识符长度  
#define FLOAT_SIZE   4          //传感器值长度
#define TIMELEN	     50       //定义时间缓冲器长度

#define BUFFER_SIZE 1024      //http数据缓冲器长度
#define HTTP_GET "GET /%s HTTP/1.1\r\nHOST: %s:%d\r\nAccept: */*\r\n\r\n"
   
typedef unsigned char u8;
typedef unsigned short u16;
typedef unsigned int u32;
   
typedef struct _CLIENT   
{   
    int fd;                     //客户端socket描述符    
    char name[20];              //客户端名称    
    struct sockaddr_in addr;    //客户端地址信息结构体    
    char data[MAXDATASIZE];     //客户端私有数据指针    
} CLIENT;   

union PARA_UNION
{
	float para;
	u32 warn;
	u8 ch[FLOAT_SIZE];
};
   
struct msg_head
{
	u16 msg_id;
	u16 msg_property;
	u16 msg_swift;	
};

void process_client(CLIENT * client, u8 *recvbuf, int len);   //客户请求处理函数    

MYSQL *g_conn; // mysql 连接
MYSQL_RES *g_res; // mysql 记录集
MYSQL_ROW g_row; // 字符串数组，mysql 记录行

const char *g_host_name = "localhost";
const char *g_user_name = "linkuser";
const char *g_password = "123456";
const char *g_db_name = "eas";
const unsigned int g_db_port = 3306;

short msg_swift = 0;        //消息流水号
FILE *fp;
char *url = "http://180.97.193.178:8888/village/syncWarnMsg?deviceSn=";
  
void print_mysql_error(const char *msg) { // 打印最后一次错误
    if (msg)
        printf("%s: %s\n", msg, mysql_error(g_conn));
    else
        puts(mysql_error(g_conn));
}
   
int init_mysql() 
{ // 初始化连接
    // init the database connection
    g_conn = mysql_init(NULL);

    /*设置字符编码,可能会乱码*/
    //mysql_query(g_conn,"set nemas utf-8");

    /* connect the database */
    if(!mysql_real_connect(g_conn, g_host_name, g_user_name, g_password, g_db_name, g_db_port, NULL, 0)) // 如果失败
        return -1;

    // 是否连接已经可用
    //if (executesql("set names utf8")) // 如果失败
       // return -1;
    return 0; // 返回成功
}  
 
void self_close()
{
	mysql_close(g_conn);
	printf("mysql disconnect success!\n");
}
  
/*往报警表中插入数据*/
void insert_warnmsg(char *device_sn,char *sensor_sn,unsigned int warn,char *warntime)
{
	int t;
	char insert_query[200] = "insert into t_warnmsg(device_sn,sensor_sn,warn,warntime) values";
	char message[200] = { 0 };

	sprintf(message, "(%s,%s,%d,'%s')", device_sn, sensor_sn, warn, warntime);
	strcat(insert_query, message);
#ifdef DBG	
	printf("%s\n", insert_query);
#endif
	fprintf(fp,"%s\n", insert_query);

	t = mysql_real_query(g_conn, insert_query, strlen(insert_query));
	if (t)
	{
		printf("failed to query:%s\n", mysql_error(g_conn));
		return;
	}
#ifdef DBG
	printf("insert warndata OK!\n");
#endif
	fprintf(fp,"%s\n", insert_query);
}
  
/*往采集表中插入数据*/
void insert_collection(char *device_sn,char *sensor_sn,float collect,char *collecttime)
{
	int t;
	char insert_query[200] = "insert into t_collection(device_sn,sensor_sn,collect,colltime) values";
	char message[200] = { 0 };

	sprintf(message, "(%s,%s,%0.4f,'%s')", device_sn, sensor_sn, collect, collecttime);
	strcat(insert_query, message);
#ifdef DBG
	printf("%s\n", insert_query);
#endif
	fprintf(fp,"%s\n", insert_query);

	t = mysql_real_query(g_conn, insert_query, strlen(insert_query));
	if (t)
	{
		printf("failed to query:%s\n", mysql_error(g_conn));
		return;
	}
#ifdef DBG
	printf("insert collectdata OK!\n");
#endif
	fprintf(fp,"insert collectdata OK!\n");
}  

/*更新设备在线时间*/
void mysql_update_device(char *device_sn,char *lasttime)
{
	int t;
	char update_query[200] = "update t_device set last_time=";
	char message[200] = { 0 };

	sprintf(message,"'%s'",lasttime);
	strcat(update_query, message);
	sprintf(message, " where device_sn='%s'", device_sn);
	strcat(update_query, message);
#ifdef DBG
	printf("%s\n", update_query);
#endif
	fprintf(fp,"%s\n", update_query);

	t = mysql_real_query(g_conn, update_query, strlen(update_query));
	if (t)
	{
		printf("failed to query:%s\n", mysql_error(g_conn));
		return;
	}
#ifdef DBG
	printf("update device lasttime OK!\n");
#endif
	fprintf(fp,"update device lasttime OK!\n");
}

   
//7e 7d字符转义
int transfer_char(u8 ch, u8 *buf)
{
	char *p;
	int ret;

	p = buf;
	if (ch == 0x7e)
	{
		*p = 0x7d;
		p++;
		*p = 0x02;
		ret = 2;
	}
	else if (ch == 0x7d)
	{
		*p = 0x7d;
		p++;
		*p = 0x01;
		ret = 2;
	}
	else
	{
		*p = ch;
		ret = 1;
	}
	return ret;
}

//数组7e 7d 转换
int transfer_array(u8 *buf, int len)
{
	u8 arr[256];
	int i,arr_len=256;
	int ret;
	u8 *p;
	
	memset(arr,0,arr_len);
	p=arr;
	ret=0;
	for(i=0;i<len;i++)
	{
		if (buf[i] == 0x7e)
		{
			*p = 0x7d;
			p++;
			*p = 0x02;
			p++;
			ret += 2;
		}
		else if (buf[i] == 0x7d)
		{
			*p = 0x7d;
			p++;
			*p = 0x01;
			p++;
			ret += 2;
		}
		else
		{
			*p = buf[i];
			p++;
			ret += 1;
		}		
	}
	memcpy(buf,arr,ret);
	return ret;
}

//反向转换7e 7d
int retrans_array(u8 *buf, int len)
{
	u8 arr[256],*p;
	int arr_len=256,i=0 , rlen=0;
	
	memset(arr,0,arr_len);
	p=arr;
	while(i<len)
	{
		if(buf[i]== 0x7e)
		{
			*p=buf[i];
			i++;
		}	
		else if((buf[i]==0x7d)&&(buf[i+1]==0x01))
		{
			*p=0x7d;
			i+=2;
		}
		else if((buf[i]==0x7d)&&(buf[i+1]==0x02))
		{
			*p=0x7e;
			i+=2;			
		}
		else
		{
			*p=buf[i];
			i++;
		}		
		p++;
		rlen++;
	}
	memcpy(buf,arr,rlen);
	return rlen;
}

//word转为发送字节
int word2bytes(u16 source, u8 *buff)
{
	int len=2;

	buff[0] = (source >> 8) & 0xff;
	buff[1] = source & 0xff;
	return len;
}

//双字转为发送字节
int dword2bytes(u32 source, u8 *buff)
{
	u16 sr;
	int ret, len;

	sr = (source >> 16) & 0xffff;
	ret = word2bytes(sr, buff);
	sr = source & 0xffff;
	len = word2bytes(sr, buff + ret);
	ret += len;
	return ret;
}

//word转为发送字节
int word2bytes_old(u16 source, u8 *buff)
{
	u8 tp;
	int ret, len;

	tp = (source >> 8) & 0xff;
	ret = transfer_char(tp, buff);
	tp = source & 0xff;
	len = transfer_char(tp, buff + ret);
	ret += len;
	return ret;
}


u8 check_num(u8 *buff, int len)
{
	int i;
	u8 chknum;

	chknum = buff[0];
	for (i = 1; i<len; i++)
	{
		chknum ^= buff[i];
	}
	return chknum;
}

//发送应答信息 
//先组合消息内容（消息头+消息体+效验码），然后进行7E 7D 处理， 最后加上7E 标志
int create_warnackmsg(u8 *buf, u8 ans, short msgid)
{
	u8 *ptr;
	u8 chknum;
	int msg_len = 0, tlen, body_len = 0;

	ptr = buf;
	*ptr = 0x7e;
	ptr++;
	*ptr=(msgid>>8) & 0xff | 0x80;
	ptr++;
	*ptr = msgid & 0xff;
	ptr++;
	tlen = word2bytes(msg_swift, ptr);      //消息流水
	ptr += tlen;
	msg_swift++;    //消息流水号加1
	*ptr = 0x00;     //应答消息长度 1字节
	ptr++;
	*ptr = 0x01;    
	ptr++;
	*ptr = ans;     //应答信息
	ptr++;
	chknum = check_num(buf + 1, ptr - buf);  //效验和
	*ptr=chknum;
	tlen = transfer_array(buf+1, ptr-buf);   //转换7e 7d
	ptr = buf+tlen+1;
	*ptr = 0x7e;
	msg_len = tlen+2;

	return msg_len;
}
   
//检查收到消息是否正确
int  check_msg(u8 *buf, int len)
{
	int ret=-1,tlen=0;

	tlen=retrans_array(buf,len);	 //7e 7d处理
	if((buf[0]==0x7e) && (buf[tlen-1]==0x7e))
	{
		if(check_num(buf+1, tlen-3)==buf[tlen-2])
			ret = 0;
		else 
			ret = -2;
	}	
	return ret;
}
 
// 发送应答消息
void ack_warnmsg(CLIENT *client,u8 ans,short msgid)
{
	u8 ackDataBuff[MAXDATASIZE]; 
	int  j,iSendSize;  
    	
	//发送应答信息
	memset(ackDataBuff, 0, MAXDATASIZE);
	iSendSize=create_warnackmsg(ackDataBuff,ans,msgid);
	iSendSize = send(client->fd, ackDataBuff, iSendSize, 0);	
	//打印发送的数据  		
#ifdef DBG
		printf("\nSend %d bytes to [%s:%d]\n", iSendSize,inet_ntoa(client->addr.sin_addr), ntohs(client->addr.sin_port));
		for (j = 0; j < iSendSize; j++)
			printf("%02x ", ackDataBuff[j]);
		printf("\n");
#endif
}

//更新设备最后在线时间
void update_device(u8 *buf, int len)
{
	char dev_id[DEVSTR_LEN+1];
	char sensor_id[5];
	u8 *ptr;
	int i;
	time_t t = time( 0 );  
	char lasttime[TIMELEN]; 	
	
	ptr=buf+sizeof(struct msg_head)+1;
	for(i=DEVSTR_LEN;i>0;i--)	          //终端标识符 5字符
	{
		dev_id[i-1]=*ptr;
		ptr++;
	}
	dev_id[DEVSTR_LEN]='\0';	    
	strftime(lasttime, TIMELEN, "%Y-%m-%d %H:%M:%S", localtime(&t)); //format date     and time.   
	//printf("time is [%s]\n",collecttime); 
	
#ifdef DBG
	printf("devid=%s lasttime=%s\n",dev_id,lasttime);
#endif
	mysql_update_device(dev_id,lasttime); //向数据库更新设备在线时间
}


//记录报警信息到数据库
void write_warnmsg(u8 *buf, int len)
{
	union PARA_UNION un;
	char dev_id[DEVSTR_LEN+1];
	char sensor_id[5];
	char newurl[50];
	u8 *ptr;
	int i,ret=0;
	time_t t = time( 0 );  
	char collecttime[TIMELEN]; 	
	
	ptr=buf+sizeof(struct msg_head)+1;
	for(i=DEVSTR_LEN;i>0;i--)	          //终端标识符 5字符
	{
		dev_id[i-1]=*ptr;
		ptr++;
	}
	dev_id[DEVSTR_LEN]='\0';
	sprintf(sensor_id,"%02d",*ptr); 	 //传感器类型
	ptr++;	
	for(i=FLOAT_SIZE;i>0;i--)	  //报警值
	{
		un.ch[i-1]=*ptr;
		ptr++;
	}	

	strftime(collecttime, TIMELEN, "%Y-%m-%d %H:%M:%S", localtime(&t)); //format date     and time.   
	//printf("time is [%s]\n",collecttime); 
	
#ifdef DBG
	printf("devid=%s type=%s  value=%d time=%s\n",dev_id,sensor_id, un.warn,collecttime);
#endif
	insert_warnmsg(dev_id,sensor_id,un.warn,collecttime);//向数据库插入采集数据
	strcat(newurl,url);
	strcat(newurl,dev_id);
#ifdef DBG
	printf("http_url:%s\n",newurl);
#endif	
	ret = http_get(newurl);
}

//记录传感器值到数据库
void write_sensor(u8 *buf, int len)
{
	union PARA_UNION un;
	char dev_id[DEVSTR_LEN+1];
	char sensor_id[5];
	u8 *ptr;
	int i;
	time_t t = time( 0 );  
	char collecttime[TIMELEN]; 	
	
	ptr=buf+sizeof(struct msg_head)+1;
	for(i=DEVSTR_LEN;i>0;i--)	          //终端标识符 5字符
	{
		dev_id[i-1]=*ptr;
		ptr++;
	}
	dev_id[DEVSTR_LEN]='\0';
	sprintf(sensor_id,"%02d",*ptr); 	 //传感器类型
	ptr++;
	for(i=FLOAT_SIZE;i>0;i--)	  //参数值
	{
		un.ch[i-1]=*ptr;
		ptr++;
	}	

	strftime(collecttime, TIMELEN, "%Y-%m-%d %H:%M:%S", localtime(&t)); //format date     and time.   
	//printf("time is [%s]\n",collecttime); 
	
#ifdef DBG
	printf("devid=%s type=%s  value=%0.4f time=%s\n",dev_id,sensor_id, un.para,collecttime);
#endif
	insert_collection(dev_id,sensor_id,un.para,collecttime); //向数据库插入采集数据
}
   
static int http_tcpclient_create(const char *host, int port)
{
    struct hostent *he;
    struct sockaddr_in server_addr; 
    int socket_fd;
 
    if((he = gethostbyname(host))==NULL){
        return -1;
    }
 
   server_addr.sin_family = AF_INET;
   server_addr.sin_port = htons(port);
   server_addr.sin_addr = *((struct in_addr *)he->h_addr);
 
    if((socket_fd = socket(AF_INET,SOCK_STREAM,0))==-1){
        return -1;
    }
 
    if(connect(socket_fd, (struct sockaddr *)&server_addr,sizeof(struct sockaddr)) == -1){
        return -1;
    }
 
    return socket_fd;
}
 
static void http_tcpclient_close(int socket)
{
    close(socket);
}
 
static int http_parse_url(const char *url,char *host,char *file,int *port)
{
    char *ptr1,*ptr2;
    int len = 0;
    if(!url || !host || !file || !port){
        return -1;
    }
 
    ptr1 = (char *)url;
 
    if(!strncmp(ptr1,"http://",strlen("http://"))){
        ptr1 += strlen("http://");
    }else{
        return -1;
    }
 
    ptr2 = strchr(ptr1,'/');
    if(ptr2){
        len = strlen(ptr1) - strlen(ptr2);
        memcpy(host,ptr1,len);
        host[len] = '\0';
        if(*(ptr2 + 1)){
            memcpy(file,ptr2 + 1,strlen(ptr2) - 1 );
            file[strlen(ptr2) - 1] = '\0';
        }
    }else{
        memcpy(host,ptr1,strlen(ptr1));
        host[strlen(ptr1)] = '\0';
    }
    //get host and ip
    ptr1 = strchr(host,':');
    if(ptr1){
        *ptr1++ = '\0';
        *port = atoi(ptr1);
    }else{
        *port = 80;
    }
 
    return 0;
}
 
 
static int http_tcpclient_recv(int socket,char *lpbuff){
    int recvnum = 0;
 
    recvnum = recv(socket, lpbuff,BUFFER_SIZE*4,0);
 
    return recvnum;
}
 
static int http_tcpclient_send(int socket,char *buff,int size){
    int sent=0,tmpres=0;
 
    while(sent < size){
        tmpres = send(socket,buff+sent,size-sent,0);
        if(tmpres == -1){
            return -1;
        }
        sent += tmpres;
    }
    return sent;
}
 
static char *http_parse_result(const char*lpbuf)
{
    char *ptmp = NULL; 
    char *response = NULL;
    ptmp = (char*)strstr(lpbuf,"HTTP/1.1");
    if(!ptmp){
        printf("http/1.1 not faind\n");
        return NULL;
    }
    if(atoi(ptmp + 9)!=200){
        printf("result:\n%s\n",lpbuf);
        return NULL;
    }
 
    ptmp = (char*)strstr(lpbuf,"\r\n\r\n");
    if(!ptmp){
        printf("ptmp is NULL\n");
        return NULL;
    }
    response = (char *)malloc(strlen(ptmp)+1);
    if(!response){
        printf("malloc failed \n");
        return NULL;
    }
    strcpy(response,ptmp+4);
    return response;
}
 
/* 
    发送http请求
*/
int  http_get(const char *url)
{
    char post[BUFFER_SIZE] = {'\0'};
    int socket_fd = -1;
    char lpbuf[BUFFER_SIZE*4] = {'\0'};
    char *ptmp;
    char host_addr[BUFFER_SIZE] = {'\0'};
    char file[BUFFER_SIZE] = {'\0'};
    int port = 0;
    int len=0;
 
    if(!url){
        printf("      failed!\n");
        return -1;
    }
 
    if(http_parse_url(url,host_addr,file,&port)){
        printf("http_parse_url failed!\n");
        return -1;
    }
    //printf("host_addr : %s\tfile:%s\t,%d\n",host_addr,file,port);
 
    socket_fd =  http_tcpclient_create(host_addr,port);
    if(socket_fd < 0){
        printf("http_tcpclient_create failed\n");
        return -1;
    }
 
    sprintf(lpbuf,HTTP_GET,file,host_addr,port);
 
    if(http_tcpclient_send(socket_fd,lpbuf,strlen(lpbuf)) < 0){
        printf("http_tcpclient_send failed..\n");
        return -1;
    }
  	//printf("sendrequest:\n%s\n",lpbuf);
 
    if(http_tcpclient_recv(socket_fd,lpbuf) <= 0){
        printf("http_tcpclient_recv failed\n");
        return -1;
    }
    http_tcpclient_close(socket_fd);
#ifdef DBG
    printf("recvans:\n%s\n",lpbuf);
#endif    
    //printf("recvans:\n%s\n",lpbuf);
    //return http_parse_result(lpbuf);
    return 0;
}   
   
/*************************************************  
* Function    : process_client()  
* Description : 处理客户端数据
* Calls       :   
* Called By   : main()  
* Input       :   
* Output      :   
* Return      :   
*************************************************/   
void process_client(CLIENT *client, u8 *recvbuf, int len)   
{   
    int i,j,msgErr; 
    short msgID; 
    u8 ans;
    
#ifdef DBG
							//打印接收的数据  
		printf("recv %s:%d recvsize:%d\n", inet_ntoa(client->addr.sin_addr), ntohs(client->addr.sin_port), len);
		for (j = 0; j < len; j++)
			printf("%02x ", recvbuf[j]);
		printf("\n");
#endif
		fprintf(fp,"recv %s:%d recvsize:%d\n", inet_ntoa(client->addr.sin_addr), ntohs(client->addr.sin_port), len);
		for (j = 0; j < len; j++)
			fprintf(fp,"%02x ", recvbuf[j]);
		fprintf(fp,"\n");
		
		if((msgErr=check_msg(recvbuf,len))==0)  //消息验证正确
		{
			msgID=(recvbuf[1]<<8) + recvbuf[2];
#ifdef DBG
			printf("msgID=%x",msgID);
#endif
			switch(msgID){
				case 0x0002:     //心跳
					ans = 0;
					ack_warnmsg(client,ans,msgID);
					update_device(recvbuf,len);
					break;
				case 0x01f0:     //报警消息
					ans=0;
					ack_warnmsg(client,ans,msgID);
					write_warnmsg(recvbuf,len);
					break;
				case 0x01f1:      //报警参数数据
					ans = 0;
					ack_warnmsg(client,ans,msgID);
					write_sensor(recvbuf,len);
					break;
				default:
					break;
			}	
		}
		else{
#ifdef DBG
			printf("msgID=%x",msgID);
#endif			
			fprintf(fp,"msgErr = %d\n",msgErr);
		}
}   
   
/*************************************************  
* Function    : main()  
* Description :   
* Calls       : process_client()  
* Called By   :   
* Input       :   
* Output      :   
* Return      :   
*************************************************/   
void main(int argc ,char **argv)   
{   
    int i, maxi, maxfd, sockfd;   
    int nready;   
    ssize_t n;   
    fd_set rset, allset;        //select所需的文件描述符集合    
    int listenfd, connectfd;    //socket文件描述符
    struct sockaddr_in server;  //服务器地址信息结构体    
   
    CLIENT client[FD_SETSIZE];  //FD_SETSIZE为select函数支持的最大描述符个数    
    char recvbuf[MAXDATASIZE];  //缓冲区    
    int sin_size;               //地址信息结构体大小   
   
		if((fp=fopen("upload.log","a+"))==NULL)
		{
			printf("Create File failure");
	  	exit(1);
		}
		else
		{
			printf("\nOpen LogFile Succ\n");
		}
    
    if (init_mysql())
    {
        print_mysql_error(NULL);  
        self_close();
        exit(1);
    }
    else
    	printf("mysql conn succ\n"); 
   
    if ((listenfd = socket(AF_INET, SOCK_STREAM, 0)) == -1)   
    {                           //调用socket创建用于监听客户端的socket    
        perror("Creating socket failed.");   
        exit(1);   
    }   
   
    int opt = SO_REUSEADDR;   
    setsockopt(listenfd, SOL_SOCKET, SO_REUSEADDR, &opt, sizeof(opt));  //设置socket属性    
   
    bzero(&server, sizeof(server));   
    server.sin_family = AF_INET;   
    server.sin_port = htons(PORT);   
    server.sin_addr.s_addr = htonl(INADDR_ANY);   
   
    if (bind(listenfd, (struct sockaddr *)&server, sizeof(struct sockaddr)) == -1)   
    {                           //调用bind绑定地址    
        perror("Bind error.");   
        exit(1);   
    }   
   
    if (listen(listenfd, BACKLOG) == -1)   
    {                           //调用listen开始监听    
        perror("listen() error\n");   
        exit(1);   
    }   
   
    //初始化select    
    maxfd = listenfd;   
    maxi = -1;   
    for (i = 0; i < FD_SETSIZE; i++)   
    {   
        client[i].fd = -1;   
    }   
    FD_ZERO(&allset);           //清空    
    FD_SET(listenfd, &allset);  //将监听socket加入select检测的描述符集合    
   
    printf("Begin Receive Data...\n");
    while (1)   
    {   
        struct sockaddr_in addr;   
        rset = allset;   
        nready = select(maxfd + 1, &rset, NULL, NULL, NULL);    //调用select    
        //printf("Select() break and the return num is %d. \n", nready);   
   
        if (FD_ISSET(listenfd, &rset))   
        {                       //检测是否有新客户端请求    
            //printf("Accept a connection.\n");   
            //调用accept，返回服务器与客户端连接的socket描述符    
            sin_size = sizeof(struct sockaddr_in);   
            if ((connectfd =   
                 accept(listenfd, (struct sockaddr *)&addr, (socklen_t *) & sin_size)) == -1)   
            {   
                perror("Accept() error\n");   
                continue;   
            }   
   
            //将新客户端的加入数组    
            for (i = 0; i < FD_SETSIZE; i++)   
            {   
                if (client[i].fd < 0)   
                {   
                    char buffer[20];   
                    client[i].fd = connectfd;   //保存客户端描述符    
                    memset(buffer, '0', sizeof(buffer));   
                    sprintf(buffer, "Client[%.2d]", i);   
                    memcpy(client[i].name, buffer, strlen(buffer));   
                    client[i].addr = addr;   
                    memset(buffer, '0', sizeof(buffer));   
                    sprintf(buffer, "Private!");   
                    memcpy(client[i].data, buffer, strlen(buffer));  
#ifdef DBG
                    printf("connection from %s:%d.\n", inet_ntoa(client[i].addr.sin_addr),ntohs(client[i].addr.sin_port));   
#endif                      
                    fprintf(fp,"connection from %s:%d.\n", inet_ntoa(client[i].addr.sin_addr),ntohs(client[i].addr.sin_port));   
                    //printf("Add a new connection:%s\n",client[i].name);   
                    break;   
                }   
            }   
               
            if (i == FD_SETSIZE)   
                printf("Too many clients\n");   
            FD_SET(connectfd, &allset); //将新socket连接放入select监听集合    
            if (connectfd > maxfd)   
                maxfd = connectfd;  //确认maxfd是最大描述符    
            if (i > maxi)       //数组最大元素值    
                maxi = i;   
            if (--nready <= 0)   
                continue;       //如果没有新客户端连接，继续循环    
        }   
   
        for (i = 0; i <= maxi; i++)   
        {   
            if ((sockfd = client[i].fd) < 0)    //如果客户端描述符小于0，则没有客户端连接，检测下一个    
                continue;   
            // 有客户连接，检测是否有数据    
            if (FD_ISSET(sockfd, &rset))   
            {   
#ifdef DBG
								printf("Receive from %s:%d.\n", inet_ntoa(client[i].addr.sin_addr),ntohs(client[i].addr.sin_port));  
#endif                 
                fprintf(fp,"Receive from %s:%d.\n", inet_ntoa(client[i].addr.sin_addr),ntohs(client[i].addr.sin_port));  
                memset(recvbuf, 0, MAXDATASIZE);  //缓冲区清0
                if ((n = recv(sockfd, recvbuf, MAXDATASIZE, 0)) == 0)   
                {               //从客户端socket读数据，等于0表示网络中断    
                    close(sockfd);  //关闭socket连接 
#ifdef DBG
                    printf("%s closed. User's data: %s\n", client[i].name, client[i].data); 
#endif                          
                    fprintf(fp,"%s closed. User's data: %s\n", client[i].name, client[i].data);   
                    FD_CLR(sockfd, &allset);    //从监听集合中删除此socket连接    
                    client[i].fd = -1;  //数组元素设初始值，表示没客户端连接    
                }   
                else {
                    process_client(&client[i], recvbuf, n); //接收到客户数据，开始处理    
                }
                if (--nready <= 0)   
                    break;      //如果没有新客户端有数据，跳出for循环回到while循环    
            }   
        }   
    }   
    close(listenfd);            //关闭服务器监听socket      
    self_close(); // 关闭数据库链接   
    fclose(fp); 
}   

