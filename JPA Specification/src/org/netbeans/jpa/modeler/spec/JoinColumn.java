//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Any modifications to this file will be lost upon recompilation of the source schema.
// Generated on: 2014.01.21 at 01:52:19 PM IST
//
package org.netbeans.jpa.modeler.spec;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.apache.commons.lang.StringUtils;
import org.eclipse.persistence.internal.jpa.metadata.columns.JoinColumnMetadata;
import org.netbeans.jpa.modeler.spec.validator.column.JoinColumnValidator;
import org.netbeans.jpa.source.JavaSourceParserUtil;

/**
 *
 *
 * @Target({METHOD, FIELD}) @Retention(RUNTIME) public @interface JoinColumn {
 * String name() default ""; String referencedColumnName() default ""; boolean
 * unique() default false; boolean nullable() default true; boolean insertable()
 * default true; boolean updatable() default true; String columnDefinition()
 * default ""; String table() default ""; ForeignKey foreignKey() default
 * @ForeignKey(); }
 *
 *
 *
 * <p>
 * Java class for join-column complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="join-column">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="referenced-column-name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="unique" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="nullable" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="insertable" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="updatable" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="column-definition" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="table" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "join-column")
@XmlJavaTypeAdapter(value=JoinColumnValidator.class)
public class JoinColumn {

    @XmlAttribute(name = "name")
    protected String name;
    @XmlAttribute(name = "referenced-column-name")
    protected String referencedColumnName;
    @XmlAttribute
    protected Boolean unique = false;
    @XmlAttribute
    protected Boolean nullable = true;
    @XmlAttribute
    protected Boolean insertable = true;
    @XmlAttribute
    protected Boolean updatable = true;
    @XmlAttribute(name = "column-definition")
    protected String columnDefinition;
    @XmlAttribute(name = "table")
    protected String table;
    
//        @XmlTransient
//    private String generatedName;

    public static JoinColumn load(Element element, AnnotationMirror annotationMirror) {
        if (annotationMirror == null) {
            annotationMirror = JavaSourceParserUtil.findAnnotation(element, "javax.persistence.JoinColumn");
        }
        JoinColumn joinColumn = null;
        if (annotationMirror != null) {
            joinColumn = new JoinColumn();
            joinColumn.name = (String) JavaSourceParserUtil.findAnnotationValue(annotationMirror, "name");
            joinColumn.referencedColumnName = (String) JavaSourceParserUtil.findAnnotationValue(annotationMirror, "referencedColumnName");
            joinColumn.unique = (Boolean) JavaSourceParserUtil.findAnnotationValue(annotationMirror, "unique");
            joinColumn.nullable = (Boolean) JavaSourceParserUtil.findAnnotationValue(annotationMirror, "nullable");
            joinColumn.insertable = (Boolean) JavaSourceParserUtil.findAnnotationValue(annotationMirror, "insertable");
            joinColumn.updatable = (Boolean) JavaSourceParserUtil.findAnnotationValue(annotationMirror, "updatable");
            joinColumn.columnDefinition = (String) JavaSourceParserUtil.findAnnotationValue(annotationMirror, "columnDefinition");
            joinColumn.table = (String) JavaSourceParserUtil.findAnnotationValue(annotationMirror, "table");
        }
        return joinColumn;

    }

    /**
     * Gets the value of the name property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the referencedColumnName property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getReferencedColumnName() {
        return referencedColumnName;
    }

    /**
     * Sets the value of the referencedColumnName property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setReferencedColumnName(String value) {
        this.referencedColumnName = value;
    }

    /**
     * Gets the value of the unique property.
     *
     * @return possible object is {@link Boolean }
     *
     */
    public Boolean getUnique() {
        return unique;
    }

    /**
     * Sets the value of the unique property.
     *
     * @param value allowed object is {@link Boolean }
     *
     */
    public void setUnique(Boolean value) {
        this.unique = value;
    }

    /**
     * Gets the value of the nullable property.
     *
     * @return possible object is {@link Boolean }
     *
     */
    public Boolean getNullable() {
        return nullable;
    }

    /**
     * Sets the value of the nullable property.
     *
     * @param value allowed object is {@link Boolean }
     *
     */
    public void setNullable(Boolean value) {
        this.nullable = value;
    }

    /**
     * Gets the value of the insertable property.
     *
     * @return possible object is {@link Boolean }
     *
     */
    public Boolean getInsertable() {
        return insertable;
    }

    /**
     * Sets the value of the insertable property.
     *
     * @param value allowed object is {@link Boolean }
     *
     */
    public void setInsertable(Boolean value) {
        this.insertable = value;
    }

    /**
     * Gets the value of the updatable property.
     *
     * @return possible object is {@link Boolean }
     *
     */
    public Boolean getUpdatable() {
        return updatable;
    }

    /**
     * Sets the value of the updatable property.
     *
     * @param value allowed object is {@link Boolean }
     *
     */
    public void setUpdatable(Boolean value) {
        this.updatable = value;
    }

    /**
     * Gets the value of the columnDefinition property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getColumnDefinition() {
        return columnDefinition;
    }

    /**
     * Sets the value of the columnDefinition property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setColumnDefinition(String value) {
        this.columnDefinition = value;
    }

    /**
     * Gets the value of the table property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getTable() {
        return table;
    }

    /**
     * Sets the value of the table property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setTable(String value) {
        this.table = value;
    }

    public JoinColumnMetadata getAccessor() {
        JoinColumnMetadata accessor = new JoinColumnMetadata();
        accessor.setColumnDefinition(columnDefinition);
        accessor.setInsertable(insertable);
        accessor.setName(name);
        accessor.setNullable(nullable);
        accessor.setReferencedColumnName(referencedColumnName);
        accessor.setTable(table);
        accessor.setUnique(unique);
        accessor.setUpdatable(updatable);
        return accessor;
    }

}
