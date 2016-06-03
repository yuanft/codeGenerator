<#include "/macro.include"/>
<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
package ${basepackage}.model;

import java.util.Date;

import com.primeton.euler.specs.capability.srm.model.ConfigInst;
import com.primeton.euler.specs.model.physical.PhysicalBase;

/**
 * TODO ${table.sqlName}
 * 
 * Created by ${author} on ${now?string("yyyy/MM/dd  HH:mm:ss")}
 */

public interface ${className}Api{
@Consumes({MediaType.APPLICATION_JSON,MediaType.TEXT_PLAIN})
@Produces({MediaType.APPLICATION_JSON,MediaType.TEXT_PLAIN})
@Path("/${className}")
public interface ${className}Api {
	
	/**
	 * 创建${className}实例
	 * 
	 * @param ${className} ${className}实例List
	 */
	@POST
	void create(List<${className}> cfgInsts) throws SrmCapabilityException;
	
	/**
	 * 更新配置项实例
	 * 
	 * @param ${className} 配置项实例List
	 * @throws SrmServiceException
	 */
	@PUT
	void update(List<${className}> cfgInsts) throws SrmCapabilityException;

	/**
	 * 删除配置项实例
	 * 
	 * @param id 配置项实例ID
	 * @throws SrmServiceException
	 */
	@DELETE
	@Path("/{id}")
	void remove(@PathParam("id") String id) throws SrmCapabilityException;

	/**
	 * 获取${className}实例
	 * 
	 * @param id ${className}实例ID
	 * @throws SrmServiceException
	 */
	@GET
	@Path("/{id}")
	${className} queryById(@PathParam("id") String id) throws SrmCapabilityException;
	
	/**
	 * 根据条件查询${className}实例
	 * 
	 * @param instCode 产品或组件CODE
	 * @param instVer  ${className}实例版本
	 * @param envId    ${className}实例环境ID
	 * @throws SrmServiceException
	 */
	@GET
	@Path("/query/{instCode}/{instVer}/{envId}")
	List<${className}> queryByCondition(@PathParam("instCode") String instCode,@PathParam("instVer") String instVer,@PathParam("envId") String envId) throws SrmCapabilityException;

	
}
