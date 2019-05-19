/* 星级评价 */

$(function() {
  var lStar = getElementsByClassName("star");
  var aLi = lStar[m].getElementsByTagName("li");
  var i = iScore = iStar = 0;
  for (i = 0; i <= aLi.length; i++) {
    aLi[i].className = "on";
  }
})

//for(var i=0;i<lStar.length;i++)
function starClick(no) {
  //alert(no);
  var lStar = getElementsByClassName("star");
  var aLi = lStar[no].getElementsByTagName("li");
  var oUl = lStar[no].getElementsByTagName("ul")[0];
  var oSpan = lStar[no].getElementsByTagName("span")[1];
  var oP = lStar[no].getElementsByTagName("p")[0];
  var i = iScore = iStar = 0;
  var aMsg = [" |非常不满", " |不满意", " |质量一般", " |质量不错", " |非常满意"]
  for (i = 1; i <= aLi.length; i++) {
    aLi[i - 1].index = i;
    //鼠标移过显示分数
    aLi[i - 1].onmouseover = function() {
      fnPoint(this.index);
      //浮动层显示
      oP.style.display = "none";
      //计算浮动层位置
      oP.style.left = oUl.offsetLeft + this.index * this.offsetWidth - 104 + "px";
      //匹配浮动层文字内容
      //oP.innerHTML = "<em><b>" + this.index + "</b> 分 " + aMsg[this.index - 1].match(/(.+)\|/)[1] + "</em>" + aMsg[this.index - 1].match(/\|(.+)/)[1]
    };
    //鼠标离开后恢复上次评分
    aLi[i - 1].onmouseout = function() {
      fnPoint();
      //关闭浮动层
      oP.style.display = "none"
    };
    //点击后进行评分处理
    aLi[i - 1].onclick = function() {
      iStar = this.index;
      oP.style.display = "none";
      //oSpan.innerHTML = "<strong>" + (this.index) + " 分</strong> (" + aMsg[this.index - 1].match(/\|(.+)/)[1] + ")";
      //document.getElementById("starLevel").value=this.index;
      //var oI=document.getElementsByTagName("input[name='starLevel']");
      var oI = document.getElementsByName("starLevel");
      oI[no].value = this.index;
      //alert(oI[no].value);
    }
  }
  //评分处理
  function fnPoint(iArg) {
    //分数赋值
    iScore = iArg || iStar;

    //document.getElementById()
    for (i = 0; i < aLi.length; i++)
      aLi[i].className = i < iScore ? "on" : "";
  }
}

function getElementsByClassName(n) {
  var classElements = [], allElements = document.getElementsByTagName('*');
  for (var i = 0; i < allElements.length; i++) {
    if (allElements[i].className == n) {
      classElements[classElements.length] = allElements[i];
    }
  }
  return classElements;
}
