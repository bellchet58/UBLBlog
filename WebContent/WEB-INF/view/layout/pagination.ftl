<#if (page??)>
<#if page.pages != 0>
	<div class="pagination" >
	<ul>
	<#if page.pages != 0>
		<#if page.pages == page.pageNum && page.pages == 1>
			<li class="disabled"><span>上一页</span></li>
		<#else> 
			<#if page.pageNum == 1>
				<li class="disabled"><span>上一页</span></li>
			<#else> 
				<li><a href="list/tagId=${page.pageNum-1}" name="last">上一页</a></li>		
			</#if>
		</#if>
	<#else> 
		<li class="disabled"><span>上一页</span></li>
	</#if>
	<#assign outPageNum = page.pageNum+9>
	<#assign firstPageNum = page.pageNum-2>
	<#if (outPageNum >page.pages) >
		<#assign outPageNum = page.pages>
	</#if>
	
	<#if (firstPageNum < 1) >
		<#assign firstPageNum = 1>
	</#if>
	
	
	<#if (firstPageNum > 1) >
		<li><a href="javascript:void(0);">1</a></li>		
	</#if>
	
	<#if (firstPageNum > 2) >	
		<li><a href="javascript:void(0);">2</a></li>		
		<#if (firstPageNum !=3) >	
		...
		</#if>
	</#if>	
	
	<#list 1..page.pages as pageNum>
	 
	 	<#if (page.pageNum == pageNum) >
			<li class="active"><a href="javascript:void(0);">${pageNum}</a></li>				
		<#else> 
			<li><a href="list/tagId=${pageNum}">${pageNum	}</a></li>							
		</#if>	
	 </#list>
	 
	 <#if (outPageNum < page.pages) >
		<li class="disabled"><span>... </span></li>
	 </#if>
	 
	<#if (page.pageNum == page.pages) >
	 	<li class="disabled"><span>下一页 </span></li>							
	<#else> 
		 <li ><a name="next" href="list/page=${page}">下一页 </a></li>							
	</#if>
	</ul>
	</div>
</#if>
</#if>









