package com.td.graph

/**
  * Created by wjl198435 on 24/7/2017.
  */
object CONSTANT {
//  val TEL="Tel"
//  val CardID="CardID"
//  val BankID="BankID"
//  val EMail="EMail"
//  val QQ="QQ"
//  val DevID="DevID"
//  val IP="IP"
//  val PartnerID="PartnerID"
//
//  val vertexID = List(TEL, CardID,BankID, EMail, QQ, DevID, IP,PartnerID)


  val elementType="type"
  val elementInV="inV"
  val elementOutV="outV"
  val elementEdge="edge"


  val eventOccurTime="eventOccurTime"


  /****define event vertex  *****/
  val EVENT="event"
  //val eventEdgeLabel={EVENT}

  val seqId="seqId"
  val eventType="eventType"
  val riskStatus="riskStatus"
  val riskScore="riskScore"
  val riskType="riskType"


//start define vertex label and property

  val accountMobile="accountMobile"
  val contactMobile="contactMobile"
  val relateMobile="relateMobile"
  val deliverMobile="deliverMobile"

  val idNumber="idNumber"
  val payeeIdNumber="payeeIdNumber"
  val contactIdNumber="contactIdNumber"
  val cardNumber="cardNumber"
  val payeeCardNumber="payeeCardNumber"
  val accountEmail="accountEmail"
  val qqNumber="qqNumber"
  val deviceId="deviceId"
  val ipAddress="ipAddress"
  val partnerCode="partnerCode"

   val vertexLabelList = List(EVENT,accountMobile, contactMobile,relateMobile,
    deliverMobile, idNumber, payeeIdNumber, contactIdNumber,cardNumber,
    payeeCardNumber,accountEmail,qqNumber,deviceId,ipAddress)


  val tagVertexProperty="tag"
  val fraudVertexProperty="fraudType"
  val ctimeVertexProperty="ctime"
  //val aTimeVertexProperty="atime"

  val vertexIDVertexProperty="vertex.id"
  val vertexPropertyKeys = List(seqId,vertexIDVertexProperty,tagVertexProperty,fraudVertexProperty,ctimeVertexProperty,eventOccurTime)
  //end define vertex label and property


  def getVertexID()={
    vertexIDVertexProperty
  }


  //start define edge label and property

  /****define action  edge  *****/
  val LOGIN = "Login" //登录事件
  val REG = "Register" //注册事件
  val TRADE = "Trade" //交易事件
  val PAYMENT = "Payment" //支付事件
  val REFUND = "Refund" //退款事件
  val LOAN = "Loan" //借款事件
  val TRSNSFER = "Transfer" //转账事件
  val WITHDRAW = "Withdraw" //提现事件
  val Modify = "Modify" //修改事件
  val CLICK = "Click" //点击事件
  val ACTIVATE = "Activate" //激活事件
  val POST = "Post" //	发帖事件
  val REVIEW = "Review" //	审核事件
  val Message = "Message" //消息事件
  val Other = "Other" //其他事件
  val Acquire = "Acquire" //收单事件
  val Recharge = "Recharge" //充值事件
  val Binding = "Binding" //绑卡事件
  val Search = "Search" //搜索事件
  val Monitor = "Monitor" //监控事件
  val SMS = "SMS" //短信事件
  val Authentication = "Authentication" //实名认证事件
  val Marketing = "Marketing" //营销事件
  val Repayment = "Repayment" //还款事件
  val Lending = "Lending" //放款事件
  val Proxypayment = "Proxypayment" //代付事件
  val Rent = "Rent" //租赁事件
  val Apply = "Apply" //申请事件
  val Grade = "Grade" //评级事件
  val Calling = "Calling" //叫车事件
  val Orders = "Orders" //接单事件
  val Driving = "Driving" //代驾事件
  val Booking = "Booking" //预约事件
  val Merchantmanaging = "Merchantmanaging" //商户管理事件
  val Surety = "Surety" //担保事件
  val Release = "Release" //发布事件
  val LoaningQuery = "LoaningQuery" //贷中查询事件
  val Confirm = "Confirm" //确认事件
  val Credit = "Credit" //授信事件
  val Barrage = "Barrage" //弹幕事件
  val AdjustAmount = "AdjustAmount" //调额事件
  val Captcha = "Captcha" //收码事件
  val LoginFeedback = "LoginFeedback" //账户验证反馈事件
  val PostLoan = "PostLoan" //贷后监控事件
  val CompanyQuery = "CompanyQuery" //企业信息查询事件
  val PreFilter = "PreFilter" //预筛事件
  val PreCredit = "PreCredit" //预授信事件
  val Account = "Account" //开户事件
  val Exchange = "Exchange" //兑换事件
  val Open = "Open" //打开事件
  val Exit = "Exit" //退出事件
  val Browse = "Browse" //浏览事件
  val Reply = "Reply" //回帖事件
  val Comment = "Comment" //评论事件
  val LiveContent = "LiveContent" //直播内容事件
  val BankThirdQuery = "BankThirdQuery" //银行三方查询事件
  val Purchase = "Purchase" //申购事件
  val IpReputation = "IpReputation" //IP画像
  val BackgroundCheck = "BackgroundCheck" //员工背调事件
  val Insure = "Insure" //投保事件
  val DebtAssess = "DebtAssess" //债权评估事件
  val GraphicRecognition = "GraphicRecognition" //图片识别事件
  val Inquiry = "Inquiry" //问诊事件
  val Unlock = "Unlock" //解锁事件
  val RegisterSuccess = "RegisterSuccess" //注册成功事件
  val MerchantPermission = "MerchantPermission" //商户准入事件
  val Edit = "Edit" //编辑资料事件

  val actionEdgeLabelList=List(
    LOGIN ,
    REG ,
    TRADE ,
    PAYMENT ,
    REFUND ,
    LOAN ,
    TRSNSFER ,
    WITHDRAW ,
    Modify ,
    CLICK ,
    ACTIVATE ,
    POST ,
    REVIEW ,
    Message ,
    Other ,
    Acquire ,
    Recharge ,
    Binding ,
    Search ,
    Monitor ,
    SMS ,
    Authentication ,
    Marketing ,
    Repayment ,
    Lending ,
    Proxypayment ,
    Rent ,
    Apply ,
    Grade ,
    Calling ,
    Orders ,
    Driving ,
    Booking ,
    Merchantmanaging ,
    Surety ,
    Release ,
    LoaningQuery ,
    Confirm ,
    Credit,
    Barrage ,
    AdjustAmount ,
    Captcha ,
    LoginFeedback ,
    PostLoan ,
    CompanyQuery ,
    PreFilter ,
    PreCredit ,
    Account ,
    Exchange ,
    Open ,
    Exit ,
    Browse ,
    Reply ,
    Comment ,
    LiveContent ,
    BankThirdQuery ,
    Purchase ,
    IpReputation ,
    BackgroundCheck ,
    Insure ,
    DebtAssess ,
    GraphicRecognition ,
    Inquiry ,
    Unlock ,
    RegisterSuccess ,
    MerchantPermission ,
    Edit )

  val actionPropertyKeys = List(eventOccurTime)
  /****end define action  edge  *****/



  /****define relative edge  *****/
  val FATHER="father"
  val MATHER="mother"
  val SPOUSE="spouse" //配偶
  val RELATIVE="relative" //亲属
  val CHILD="child"  //小孩
  val FRIEND="friend"
  val CoBorrower="coBorrower" //共同借贷
  val GUARANTOR="guarantor" //担保人
  val OTHER="other"  //其他关系

  val relationEdgeLabelList=List(FATHER,MATHER,SPOUSE,RELATIVE,CHILD,FRIEND,CoBorrower,GUARANTOR,OTHER)
  /****end  define relative edge  *****/




  val eventPropertyKeysList=List(eventOccurTime,eventType,riskStatus,riskScore,riskType,partnerCode,ctimeVertexProperty)
  /****end  define event edge  *****/

  //end define edge label and property

   //def useUserSuppliedIds: String = return vertexIDVertexProperty

}
