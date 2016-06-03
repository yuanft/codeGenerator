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

public class ${className}PO extends PhysicalBase{
	
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "${table.tableAlias}";
	<#list table.columns as column>
	
	//${column.columnAlias}
	private String ${column.columnNameLower};
	</#list>
	
	//date formats
	<#list table.columns as column>
	<#if column.isDateTimeColumn>
	
	//${column.columnAlias}
	private Date ${column.columnNameLower};
	</#if>
	</#list>
	
	// Constructors

	/** default constructor */
	public ${className}PO() {

	}
	
	/** full constructor */
	public String ${className}PO(<#list table.columns as column><#if column.isDateTimeColumn>Date ${column.columnNameLower},</#if></#list><#list table.columns as column><#if column_has_next>String ${column.columnNameLower},<#else>String ${column.columnNameLower}</#if></#list>) {
	<#list table.columns as column>
		this.${column.columnNameLower} = ${column.columnNameLower};
	</#list>
	}
	
	<@generateFields/>
	<@generatePkProperties/>
	<@generateNotPkProperties/>
	<@generateJavaOneToMany/>
	<@generateJavaManyToOne/>
	public int hashCode() {
		return new HashCodeBuilder()<#list table.pkColumns as column><#if !table.compositeId>.append(get${column.columnName}())</#if></#list>.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof ${className} == false) return false;
		if(this == obj) return true;
		${className} other = (${className})obj;
		return new EqualsBuilder()<#list table.pkColumns as column><#if !table.compositeId>.append(get${column.columnName}(),other.get${column.columnName}())</#if></#list>.isEquals();
	}
}

<#macro generateFields>
<#if table.compositeId>
	private ${className}Id id;
	<#list table.columns as column>
		<#if !column.pk>
	private ${column.javaType} ${column.columnNameLower};
		</#if>
	</#list>
</#if>
</#macro>

<#macro generatePkProperties>
<#if table.compositeId>
	@EmbeddedId
	public ${className}Id getId() {
		return this.id;
	}
	public void setId(${className}Id id) {
		this.id = id;
	}
	
<#else>
	<#list table.columns as column>
		<#if column.pk>
	public void set${column.columnName}(${column.javaType} value) {
		this.${column.columnNameLower} = value;
	}
	public ${column.javaType} get${column.columnName}() {
		return this.${column.columnNameLower};
	}
		</#if>
	</#list>
</#if>
	
</#macro>

<#macro generateNotPkProperties>
<#list table.columns as column>
	<#if !column.pk>
		<#if column.isDateTimeColumn>
	public String get${column.columnName}String() {
		return this.${column.columnName}; 
	}
	public void set${column.columnName}String(Date ${column.columnName}) {
		this.${column.columnName} = ${column.columnName};	
	}
	
		</#if>
	public ${column.javaType} get${column.columnName}() {
		return this.${column.columnNameLower};
	}
	public void set${column.columnName}(${column.javaType} value) {
		this.${column.columnNameLower} = value;
	}
	
	</#if>
</#list>
</#macro>

<#macro generateJavaOneToMany>
	<#list table.exportedKeys.associatedTables?values as foreignKey>
	<#assign fkSqlTable = foreignKey.sqlTable>
	<#assign fkTable    = fkSqlTable.className>
	<#assign fkPojoClass = fkSqlTable.className>
	<#assign fkPojoClassVar = fkPojoClass?uncap_first>
	
	private Set ${fkPojoClassVar}s = new HashSet(0);
	public void set${fkPojoClass}s(Set<${fkPojoClass}> ${fkPojoClassVar}){
		this.${fkPojoClassVar}s = ${fkPojoClassVar};
	}
	
	@OneToMany(cascade = { CascadeType.MERGE }, fetch = FetchType.LAZY, mappedBy = "${classNameLower}")
	public Set<${fkPojoClass}> get${fkPojoClass}s() {
		return ${fkPojoClassVar}s;
	}
	</#list>
</#macro>

<#macro generateJavaManyToOne>
	<#list table.importedKeys.associatedTables?values as foreignKey>
	<#assign fkSqlTable = foreignKey.sqlTable>
	<#assign fkTable    = fkSqlTable.className>
	<#assign fkPojoClass = fkSqlTable.className>
	<#assign fkPojoClassVar = fkPojoClass?uncap_first>
	
	private ${fkPojoClass} ${fkPojoClassVar};
	public void set${fkPojoClass}(${fkPojoClass} ${fkPojoClassVar}){
		this.${fkPojoClassVar} = ${fkPojoClassVar};
	}
	
	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumns({
	<#list foreignKey.parentColumns?values as fkColumn>
		@JoinColumn(name = "${fkColumn}",nullable = false, insertable = false, updatable = false) <#if fkColumn_has_next>,</#if>
    </#list>
	})
	public ${fkPojoClass} get${fkPojoClass}() {
		return ${fkPojoClassVar};
	}
	</#list>
</#macro>

