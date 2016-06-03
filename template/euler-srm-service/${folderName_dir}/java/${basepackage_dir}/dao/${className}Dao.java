<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>   
package ${basepackage}.dao;

import org.apache.ibatis.annotations.Param;

import com.primeton.euler.srm.service.model.${className}PO;

<#include "/java_imports.include">

/**
 * TODO ${table.sqlName}
 * 
 * Created by ${author} on ${now?string("yyyy/MM/dd  HH:mm:ss")}
 */

public interface ${className}Dao {
	
	
	/**
	 * 创建${table.sqlName}实例
	 * @param ${className}
	 * @return
	 */
	void create(${className}PO ${className});

	
	/**
	 * 修改${table.sqlName}实例
	 * @param ${className}
	 * @return
	 */
	void update(${className}PO ${className});
	
	/**
	 * 删除${table.sqlName}实例，逻辑删除
	 * @param id ${className}实例ID
	 * @return
	 */
	void logicDelete(@Param("id") String id); 
	
	/**
	 * 删除${table.sqlName}实例，物理删除
	 * @param id ${className}实例ID
	 * @return
	 */
	void remove(@Param("id") String id);
	
	/**
	 * 根据${table.sqlName}实例ID查询${className}实例
	 * @param id ${className}实例ID
	 * @return
	 */
	${className}PO queryById(@Param("id") String id);
	
	/**
	 * 根据条件查询${className}实例
	 * @param instId   产品或组件实例ID
	 * @param envId    ${className}实例环境ID
	 * @param tenantId 租户ID
	 * @return
	 */
	List<${className}PO> queryByCondition(@Param("instId") String instId,@Param("envId") String envId,@Param("tenantId") String tenantId);

}
