<!--导航栏-->
<div class="bg-nav">
	<ul class="nav nav-pills nav-stacked">                   
      <li class="">
	  	<a href="${rc.contextPath}/admin/ucenter">添加新文章</a>
	  <li>
	  <li class="">
	  	<a href="${rc.contextPath}/admin/articles">查看文章</a>
	  <li>  
	  <li class="">
	  	<a href="${rc.contextPath}/admin/categorys">分类管理</a>
	  <li>
	  <li class="">
	  	<a href="${rc.contextPath}/admin/tags">标签管理</a>
	  <li>
	  <li class="">
	  	<a href="${rc.contextPath}/admin/frlink">友情链接管理</a>
	  <li>
	  <li class="">
	  	<a href="${rc.contextPath}/admin/page/list">页面管理</a>
	  <li> 
	  <li class="">
	  	<a href="${rc.contextPath}/ucenter/userInfo">用户资料管理</a>
	  <li>  
	  <li class="">
	  	<a href="${rc.contextPath}/ucenter/account">账户密码管理</a>
	  <li> 
	  <li class="">
	  	<a href="${rc.contextPath}/article/index">返回首页</a>
	  <li>                    
    </ul>
    <p>		
    	<#if Session.UserID?exists>
			<a href="javascript:;">${Session.UserName}</a> 已登录,
			<a href="${rc.contextPath}/user/logout">退出</a>
		</#if>
	</p>
	<p>©Ziv小威 2012.Design By <a href="${rc.contextPath}/article/nav">Ziv</a></p>		
</div>