//判断严重隐患用
var serious_i = 
[
 {lid:11, keys:'金属冶炼企业主要负责人和安全生产管理人员依法经考核合格'},
 {lid:25, keys:'制定粉尘清扫制度，作业现场积尘未及时规范清理'},
 {lid:49, keys:'水泥工厂筒型储存库人工清库作业外包给不具备高空作业工程专业承包资质的承包方且作业前未进行风险分析'},
 {lid:104, keys:'包装间、分割间、产品整理间等人员较多生产场所的空调系统采用氨直接蒸发制冷系统'},
 {lid:104, keys:'快速冻结装置未设置在单独的作业间内，且作业间内作业人员数量超过9人'},
 {lid:105, keys:'粉尘爆炸危险场所设置在非框架结构的多层建构筑物内，或与居民区、员工宿舍、会议室等人员密集场所安全距离不足'},
 {lid:106, keys:'粉尘爆炸危险场所的20区使用防爆电气设备设施'},
 {lid:108, keys:'可燃性粉尘与可燃气体等易加剧爆炸危险的介质不能共用一套除尘系统，不同防火分区的除尘系统不能互联互通'},
 {lid:108, keys:'干式除尘系统应规范采用泄爆、隔爆、惰化、抑爆等任一种控爆措施'},
 {lid:108, keys:'除尘系统采用粉尘沉降室除尘，或者采用干式巷道式构筑物作为除尘风道'},
 {lid:153, keys:'对有限空间作业场所进行辨识，并设置明显安全警示标志'},
 {lid:153, keys:'落实作业审批制度，擅自进入有限空间作业'},
 {lid:222, keys:'保险粉、双氧水、亚氯酸钠、雕白粉（吊白块）等危险品与禁忌物料混合贮存的；保险粉露天堆放，或储存场所未采取防水、防潮等措施'},
 {lid:234, keys:'纱、线、织物加工的烧毛、开幅、烘干等热定型工艺的汽化室、燃气贮罐、储油罐、热媒炉等未与生产加工、人员密集场所明确分开或单独设置'},
 {lid:354, keys:'涂装调漆间和喷漆室未规范设置可燃气体报警装置和防爆电气设备设施'},
 {lid:354, keys:'天然气（煤气）加热炉燃烧器操作部位未设置可燃气体泄漏报警装置，或燃烧系统未设置防突然熄火或点火失败的安全装置'},
 {lid:354, keys:'使用易燃易爆稀释剂（如天拿水）清洗设备设施，未采取有效措施及时清除集聚在地沟、地坑等有限空间内的可燃气体'},
 {lid:365, keys:'会议室、活动室、休息室、更衣室等场所设置在熔炼炉、熔融金属吊运和浇注影响范围内'},
 {lid:401, keys:'进入筒型储库、磨机、破碎机、篦冷机、各种焙烧窑等有限空间作业时，采取有效的防止电气设备意外启动、热气涌入等隔离防护措施'},
 {lid:401, keys:'玻璃窑炉、玻璃锡槽，水冷、风冷保护系统存在漏水、漏气，设置监测报警装置'},
 {lid:418, keys:'水泥工厂煤磨袋式收尘器（或煤粉仓）设置温度和一氧化碳监测，或设置气体灭火装置'},
 {lid:420, keys:'燃气窑炉设置燃气低压警报器和快速切断阀，或易燃易爆气体聚集区域设置监测报警装置'},
 {lid:479, keys:'使用煤气（天然气）的烧嘴等燃烧装置，应设置防突然熄火或点火失败的快速切断阀，以切断煤气（天然气）'},
 {lid:480, keys:'冶炼炉窑的水冷元件应配置温度、进出水流量差检测及报警装置；应设置防止冷却水大量进入炉内的安全设施（如：快速切断阀等）'},
 {lid:485, keys:'炉、窑、槽、罐类设备本体及附属设施应定期检查，出现严重焊缝开裂、腐蚀、破损、衬砖损坏、壳体发红及明显弯曲变形等未报修或报废，不应继续使用'},
 {lid:485, keys:'吊运铜水等熔融有色金属及渣的起重机要符合冶金起重机的相关要求；横梁、耳轴销和吊钩、钢丝绳及其端头固定零件，进行定期检查，发现问题及时处理'},
 {lid:485, keys:'盛装铜水等熔融有色金属及渣的罐（包、盆）等容器耳轴定期进行检测'},
 {lid:597, keys:'煤气柜建设在居民稠密区，远离大型建筑、仓库、通信和交通枢纽等重要设施；附属设备设施按防火防爆要求配置防爆型设备；柜顶设置防雷装置'},
 {lid:597, keys:'煤气区域的值班室、操作室等人员较集中的地方，设置固定式一氧化碳监测报警装置'},
 {lid:600, keys:'盛装铁水、钢水与液渣的罐（包、盆）等容器耳轴按国家标准规定要求定期进行探伤检测'},
 {lid:600, keys:'炉、窑、槽、罐类设备本体及附属设施应定期检查，出现严重焊缝开裂、腐蚀、破损、衬砖损坏、壳体发红及明显弯曲变形等未报修或报废，不应继续使用'},
 {lid:601, keys:'氧枪等水冷元件应配置出水温度与进出水流量差检测、报警装置及温度监测，且与炉体倾动、氧气开闭等联锁'},
 {lid:602, keys:'会议室、活动室、休息室、更衣室等场所不应设置在铁水、钢水与液渣吊运影响的范围内'},
 {lid:655, keys:'构成一级、二级重大危险源的危险化学品罐区实现紧急切断功能；涉及毒性气体、液化气体、剧毒液体的一级、二级重大危险源的危险化学品罐区配备独立的安全仪表系统'},
 {lid:656, keys:'不得使用淘汰落后安全技术工艺、设备目录列出的工艺、设备'},
 {lid:656, keys:'新开发的危险化学品生产工艺经小试、中试、工业化试验直接进行工业化生产'},
 {lid:656, keys:'国内首次使用的化工工艺经过省级人民政府有关部门组织的安全可靠性论证'},
 {lid:656, keys:'新建装置制定试生产方案投料开车'},
 {lid:656, keys:'精细化工企业按规范性文件要求开展反应安全风险评估'},
 {lid:658, keys:'涉及可燃和有毒有害气体泄漏的场所未按国家标准设置检测报警装置，爆炸危险场所按国家标准安装使用防爆电气设备'},
 {lid:658, keys:'控制室或机柜间面向具有火灾、爆炸危险性装置一侧要满足国家标准关于防火防爆的要求'},
 {lid:658, keys:'化工生产装置按国家标准要求设置双重电源供电，自动化控制系统设置不间断电源'},
 {lid:658, keys:'安全阀、爆破片等安全附件正常投用'},
 {lid:658, keys:'涉及重点监管危险化工工艺的装置实现自动化控制，系统实现紧急停车功能，装备的自动化控制系统、紧急停车系统投入使用'},
 {lid:659, keys:'按国家标准分区分类储存危险化学品，超量、超品种储存危险化学品，相互禁配物质混放混存'},
 {lid:661, keys:'全压力式液化烃储罐按国家标准设置注水措施'},
 {lid:662, keys:'液化烃、液氨、液氯等易燃易爆、有毒有害液化气体的充装使用万向管道充装系统'},
 {lid:662, keys:'光气、氯气等剧毒气体及硫化氢气体管道不得穿越除厂区(包括化工园区、工业园区）外的公共区域'},
 {lid:667, keys:'地区架空电力线路穿越生产区且符合国家标准要求'},
 {lid:667, keys:'涉及“两重点一重大”的生产装置、储存设施外部安全防护距离符合国家标准要求'},
 {lid:679, keys:'使用煤气（天然气）的烧嘴等燃烧装置，应设置防突然熄火或点火失败的快速切断阀，以切断煤气（天然气）'},
 {lid:680, keys:'冶炼炉窑的水冷元件应配置温度、进出水流量差检测及报警装置；应设置防止冷却水大量进入炉内的安全设施（如：快速切断阀等）'},
 {lid:685, keys:'吊运铜水等熔融有色金属及渣的起重机要符合冶金起重机的相关要求；横梁、耳轴销和吊钩、钢丝绳及其端头固定零件，进行定期检查，发现问题及时处理'},
 {lid:685, keys:'盛装铜水等熔融有色金属及渣的罐（包、盆）等容器耳轴定期进行检测'},
 {lid:685, keys:'炉、窑、槽、罐类设备本体及附属设施应定期检查，出现严重焊缝开裂、腐蚀、破损、衬砖损坏、壳体发红及明显弯曲变形等未报修或报废，不应继续使用'},
 {lid:757, keys:'食品制造企业涉及烘制、油炸等设施设备，应采取防过热自动报警切断装置和隔热防护措施'},
 {lid:811, keys:'白酒储存、勾兑场所应规范设置乙醇浓度检测报警装置'},
 {lid:927, keys:'喷涂车间、调漆间规范设置通风装置和防爆电气设备设施'},
 {lid:955, keys:'危险化学品生产、经营单位主要负责人和安全生产管理人员依法经考核合格'},
 {lid:977, keys:'制定操作规程和工艺控制指标'},
 {lid:980, keys:'特种作业人员持证上岗'},
 {lid:983, keys:'建立与岗位相匹配的全员安全生产责任制或者制定实施生产安全事故隐患排查治理制度'},
 {lid:3241, keys:'按照国家标准制定动火、进入受限空间等特殊作业管理制度，或者制度未有效执行'},
]