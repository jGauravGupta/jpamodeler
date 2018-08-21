//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Any modifications to this file will be lost upon recompilation of the source schema.
// Generated on: 2014.01.21 at 01:52:19 PM IST
//
package io.github.jeddict.jpa.spec;

import static io.github.jeddict.jcode.JPAConstants.MANY_TO_ONE_FQN;
import io.github.jeddict.jpa.spec.extend.SingleRelationAttribute;
import io.github.jeddict.source.AnnotationExplorer;
import io.github.jeddict.source.JavaSourceParserUtil;
import io.github.jeddict.source.MemberExplorer;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.VariableElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 *
 * @Target({METHOD, FIELD}) @Retention(RUNTIME) public @interface ManyToOne {
 * Class targetEntity() default void.class; CascadeType[] cascade() default {};
 * FetchType fetch() default EAGER; boolean optional() default true; }
 *
 *
 *
 * <p>
 * Java class for many-to-one complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="many-to-one">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;choice>
 *           &lt;sequence>
 *             &lt;element name="join-column" type="{http://xmlns.jcp.org/xml/ns/persistence/orm}join-column" maxOccurs="unbounded" minOccurs="0"/>
 *             &lt;element name="foreign-key" type="{http://xmlns.jcp.org/xml/ns/persistence/orm}foreign-key" minOccurs="0"/>
 *           &lt;/sequence>
 *           &lt;element name="join-table" type="{http://xmlns.jcp.org/xml/ns/persistence/orm}join-table" minOccurs="0"/>
 *         &lt;/choice>
 *         &lt;element name="cascade" type="{http://xmlns.jcp.org/xml/ns/persistence/orm}cascade-type" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="target-entity" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="fetch" type="{http://xmlns.jcp.org/xml/ns/persistence/orm}fetch-type" />
 *       &lt;attribute name="optional" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="access" type="{http://xmlns.jcp.org/xml/ns/persistence/orm}access-type" />
 *       &lt;attribute name="maps-id" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "many-to-one")
@XmlRootElement
public class ManyToOne extends SingleRelationAttribute {

    @Override
    public ManyToOne load(EntityMappings entityMappings, Element element, VariableElement variableElement, ExecutableElement getterElement, AnnotationMirror annotationMirror) {
        if (annotationMirror == null) {
            annotationMirror = JavaSourceParserUtil.findAnnotation(element, MANY_TO_ONE_FQN);
        }
        super.loadAttribute(entityMappings, element, variableElement, getterElement, annotationMirror);
        return this;
    }

//  @Override
    public ManyToOne load(MemberExplorer member) {
        AnnotationExplorer annotation = member.getAnnotation(javax.persistence.ManyToOne.class).get();
        super.loadAttribute(member, annotation);
        return this;
    }

    /**
     * @return the owner
     */
    @Override
    public boolean isOwner() {
        return true;//always owner
    }

    /**
     * @param owner the owner to set
     */
    @Override
    public void setOwner(boolean owner) {
        //skip
    }

}
