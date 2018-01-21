/**
 * Copyright [2018] Gaurav Gupta
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.netbeans.orm.converter.compiler.def;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;
import org.apache.commons.lang3.StringUtils;
import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.netbeans.jcode.core.util.JavaIdentifiers.getGenericType;
import static org.netbeans.jcode.core.util.JavaIdentifiers.unqualifyGeneric;
import org.netbeans.jcode.core.util.JavaSourceHelper;
import org.netbeans.jpa.modeler.settings.code.CodePanel;
import org.netbeans.jpa.modeler.spec.extend.ClassAnnotationLocationType;
import org.netbeans.jpa.modeler.spec.extend.ClassSnippetLocationType;
import org.netbeans.orm.converter.compiler.AnnotationSnippet;
import org.netbeans.orm.converter.compiler.ConstructorSnippet;
import org.netbeans.orm.converter.compiler.EqualsMethodSnippet;
import org.netbeans.orm.converter.compiler.HashcodeMethodSnippet;
import org.netbeans.orm.converter.compiler.InvalidDataException;
import org.netbeans.orm.converter.compiler.Snippet;
import org.netbeans.orm.converter.compiler.ToStringMethodSnippet;
import org.netbeans.orm.converter.spec.WritableSnippet;
import org.netbeans.orm.converter.util.ClassHelper;
import org.netbeans.orm.converter.util.ImportSet;
import org.netbeans.orm.converter.util.ORMConverterUtil;
import static org.netbeans.orm.converter.util.ORMConverterUtil.NEW_LINE;
import static org.netbeans.orm.converter.util.ORMConverterUtil.eliminateSamePkgImports;
import static org.netbeans.orm.converter.util.ORMConverterUtil.processedImportStatements;

public abstract class ClassDefSnippet implements WritableSnippet {


    private List<ConstructorSnippet> constructorSnippets;
    private HashcodeMethodSnippet hashcodeMethodSnippet;
    private EqualsMethodSnippet equalsMethodSnippet;
    private ToStringMethodSnippet toStringMethodSnippet;

    private List<Snippet> jsonbSnippets;
    private Map<ClassSnippetLocationType, List<String>> customSnippet;
    private Map<ClassAnnotationLocationType, List<AnnotationSnippet>> annotation;

    private boolean defaultClass = false;
    private boolean beanClass = false;
    private boolean _abstract = false;
    private List<String> interfaces;

    private final ClassHelper classHelper = new ClassHelper();
    private final ClassHelper superClassHelper = new ClassHelper();
    private String description;
    private String author;

    private boolean propertyChangeSupport;
    private boolean vetoableChangeSupport;

    private List<VariableDefSnippet> variableDefs = new ArrayList<>();
    private boolean jaxbSupport;
    private boolean xmlRootElement;

    public boolean isJaxbSupport() {
        return jaxbSupport;
    }

    public void setJaxbSupport(boolean jaxbSupport) {
        this.jaxbSupport = jaxbSupport;
    }

    public boolean isXmlRootElement() {
        return xmlRootElement;
    }

    public void setXmlRootElement(boolean xmlRootElement) {
        this.xmlRootElement = xmlRootElement;
    }

    public String getPackageName() {
        return classHelper.getPackageName();
    }

    public void setPackageName(String packageName) {
        this.classHelper.setPackageName(packageName);
    }

    public String getClassName() {
        return classHelper.getClassName();
    }

    @Override
    public ClassHelper getClassHelper() {
        return classHelper;
    }

    public ClassHelper getSuperClassHelper() {
        return superClassHelper;
    }

    public void setClassName(String className) {
        classHelper.setClassName(className);
    }

    public String getSuperClassName() {
        return superClassHelper.getClassDeclarationWithFQGeneric();
    }

    public void setSuperClassName(String className) {
        superClassHelper.setClassName(className);
    }

    public String getManagedType() {
        return EMPTY;
    }

    public void setVariableDefs(List<VariableDefSnippet> variableTypes) {
        this.variableDefs = variableTypes;
    }

    public List<VariableDefSnippet> getVariableDefs() {
        return variableDefs;
    }

    protected abstract String getTemplateName();

    /**
     * @return the defaultClass
     */
    public boolean isDefaultClass() {
        return defaultClass;
    }

    /**
     * @param defaultClass the defaultClass to set
     */
    public void setDefaultClass(boolean defaultClass) {
        this.defaultClass = defaultClass;
    }

    /**
     * @return the beanClass
     */
    public boolean isBeanClass() {
        return beanClass;
    }

    /**
     * @param beanClass the beanClass to set
     */
    public void setBeanClass(boolean beanClass) {
        this.beanClass = beanClass;
    }

    /**
     * @return the annotation
     */
    public Map<ClassAnnotationLocationType, List<AnnotationSnippet>> getAnnotation() {
        return annotation;
    }

    public List<AnnotationSnippet> getAnnotation(String locationType) {
        return annotation.get(ClassAnnotationLocationType.valueOf(locationType));
    }

    /**
     * @param annotation the annotation to set
     */
    public void setAnnotation(Map<ClassAnnotationLocationType, List<AnnotationSnippet>> annotation) {
        this.annotation = annotation;
    }

    /**
     * @return the abstractClass
     */
    public boolean isAbstractClass() {
        return _abstract;
    }

    /**
     * @param abstractClass the abstractClass to set
     */
    public void setAbstractClass(boolean abstractClass) {
        this._abstract = abstractClass;
    }

    /**
     * @return the interfaces
     */
    public List<String> getInterfaces() {
        if (interfaces == null) {
            interfaces = new ArrayList<>();
        }
        return interfaces;
    }

    /**
     * @param interfaces the interfaces to set
     */
    public void setInterfaces(List<String> interfaces) {
        this.interfaces = interfaces;
    }

    public boolean isInterfaceExist() {
        return interfaces != null && !interfaces.isEmpty();
    }

    public String getUnqualifiedInterfaceList() {
        return interfaces.stream().map(fqn -> unqualifyGeneric(fqn) + getGenericType(fqn)).collect(joining(", "));
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    public String getJavaDoc() {
        StringBuilder doc = new StringBuilder();
        if (StringUtils.isNotBlank(description) || StringUtils.isNotBlank(author)) {
            doc.append(NEW_LINE).append("/**").append(NEW_LINE);
            if (StringUtils.isNotBlank(description)) {
                for (String line : description.split("\\r\\n|\\n|\\r")) {
                    doc.append(" * ").append(line).append(NEW_LINE);
                }
            }
            if (StringUtils.isNotBlank(author)) {
                doc.append(" * @author ").append(author).append(NEW_LINE);
            }
            doc.append(" */");
        }
        return doc.toString();
    }

    public boolean isJavaDocExist() {
        return StringUtils.isNotBlank(description) || StringUtils.isNotBlank(JavaSourceHelper.getAuthor());
    }

    /**
     * @return the toStringMethodSnippet
     */
    public ToStringMethodSnippet getToStringMethod() {
        return toStringMethodSnippet;
    }

    /**
     * @param toStringMethodSnippet the toStringMethodSnippet to set
     */
    public void setToStringMethod(ToStringMethodSnippet toStringMethodSnippet) {
        this.toStringMethodSnippet = toStringMethodSnippet;
    }

    /**
     * @return the hashcodeMethodSnippet
     */
    public HashcodeMethodSnippet getHashcodeMethod() {
        return hashcodeMethodSnippet;
    }

    /**
     * @param hashcodeMethodSnippet the hashcodeMethodSnippet to set
     */
    public void setHashcodeMethod(HashcodeMethodSnippet hashcodeMethodSnippet) {
        this.hashcodeMethodSnippet = hashcodeMethodSnippet;
    }

    /**
     * @return the equalsMethodSnippet
     */
    public EqualsMethodSnippet getEqualsMethod() {
        return equalsMethodSnippet;
    }

    /**
     * @param equalsMethodSnippet the equalsMethodSnippet to set
     */
    public void setEqualsMethod(EqualsMethodSnippet equalsMethodSnippet) {
        this.equalsMethodSnippet = equalsMethodSnippet;
    }

    /**
     * @return the constructorSnippets
     */
    public List<ConstructorSnippet> getConstructors() {
        if (constructorSnippets == null) {
            constructorSnippets = new ArrayList<>();
        }
        return constructorSnippets;
    }

    /**
     * @param constructorSnippets the constructorSnippets to set
     */
    public void setConstructors(List<ConstructorSnippet> constructorSnippets) {
        this.constructorSnippets = constructorSnippets;
    }

    public boolean addConstructor(ConstructorSnippet constructorSnippet) {
        return getConstructors().add(constructorSnippet);
    }

    public boolean removeConstructor(ConstructorSnippet constructorSnippet) {
        return getConstructors().remove(constructorSnippet);
    }

    /**
     * @return the customSnippet
     */
    public Map<ClassSnippetLocationType, List<String>> getCustomSnippet() {
        return customSnippet;
    }

    public List<String> getCustomSnippet(String type) {
        return customSnippet.get(ClassSnippetLocationType.valueOf(type));
    }

    /**
     * @param customSnippet the customSnippet to set
     */
    public void setCustomSnippet(Map<ClassSnippetLocationType, List<String>> customSnippet) {
        this.customSnippet = customSnippet;
    }

    /**
     * @return the author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * @param author the author to set
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * @return the propertyChangeSupport
     */
    public boolean isPropertyChangeSupport() {
        return propertyChangeSupport;
    }

    /**
     * @param propertyChangeSupport the propertyChangeSupport to set
     */
    public void setPropertyChangeSupport(boolean propertyChangeSupport) {
        this.propertyChangeSupport = propertyChangeSupport;
    }

    /**
     * @return the vetoableChangeSupport
     */
    public boolean isVetoableChangeSupport() {
        return vetoableChangeSupport;
    }

    /**
     * @param vetoableChangeSupport the vetoableChangeSupport to set
     */
    public void setVetoableChangeSupport(boolean vetoableChangeSupport) {
        this.vetoableChangeSupport = vetoableChangeSupport;
    }

    /**
     * @return the classJSONBSnippets
     */
    public List<Snippet> getJSONBSnippets() {
        return jsonbSnippets;
    }

    /**
     * @param classJSONBSnippets the classJSONBSnippets to set
     */
    public void setJSONBSnippets(List<Snippet> classJSONBSnippets) {
        this.jsonbSnippets = classJSONBSnippets;
    }

    @Override
    public String getSnippet() throws InvalidDataException {
        try {
            Map velocityContext = new HashMap();
            velocityContext.put("classDef", this);
            velocityContext.put("n", NEW_LINE);
            velocityContext.put("fluentAPI", CodePanel.isGenerateFluentAPI());
            return ORMConverterUtil.writeToTemplate(getTemplateName(), velocityContext);
        } catch (Exception e) {
            throw new InvalidDataException("Class name : " + classHelper.getFQClassName(), e);
        }
    }

    @Override
    public Collection<String> getImportSnippets() throws InvalidDataException {
        Collection<String> importSnippets = getImportSet();
        importSnippets = eliminateSamePkgImports(classHelper.getPackageName(), importSnippets);
        return processedImportStatements(importSnippets);
    }

    public ImportSet getImportSet() throws InvalidDataException {
        ImportSet importSnippets = new ImportSet();

        if (getEqualsMethod() != null) {
            importSnippets.addAll(getEqualsMethod().getImportSnippets());
        }

        if (getHashcodeMethod() != null) {
            importSnippets.addAll(getHashcodeMethod().getImportSnippets());
        }

        if (getToStringMethod() != null) {
            importSnippets.addAll(getToStringMethod().getImportSnippets());
        }

        if (superClassHelper.getPackageName() != null) {
            importSnippets.add(superClassHelper.getFQClassName());
        }

        if (!variableDefs.isEmpty()) {
            for (VariableDefSnippet variableDef : variableDefs) {
                importSnippets.addAll(variableDef.getImportSnippets());
            }
        }

        for (AnnotationSnippet snippet : this.getAnnotation().values().stream()
                .flatMap(annot -> annot.stream()).collect(toList())) {
            importSnippets.addAll(snippet.getImportSnippets());
        }

        importSnippets.addAll(this.getInterfaces().stream().collect(toList()));

        for (Snippet snippet : this.getJSONBSnippets()) {
            importSnippets.addAll(snippet.getImportSnippets());
        }

        return importSnippets;
    }

}