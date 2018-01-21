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
package org.netbeans.jpa.modeler.core.widget.flow.association;

import org.netbeans.jpa.modeler.core.widget.attribute.association.AssociationAttributeWidget;
import org.netbeans.jpa.modeler.specification.model.scene.JPAModelerScene;import org.netbeans.modeler.specification.model.document.IModelerScene;
import org.netbeans.modeler.specification.model.document.widget.IFlowElementWidget;
import org.netbeans.modeler.widget.edge.info.EdgeWidgetInfo;

/**
 *
 * @author Gaurav_Gupta
 */
public class BMTMAssociationFlowWidget extends MTMAssociationFlowWidget implements BidirectionalAssociation {

    private AssociationAttributeWidget targetAssociationAttributeWidget;

    public BMTMAssociationFlowWidget(JPAModelerScene scene, EdgeWidgetInfo edge) {
        super(scene, edge);
    }

    /**
     * @return the targetAssociationAttributeWidget
     */
    @Override
    public AssociationAttributeWidget getTargetAssociationAttributeWidget() {
        return targetAssociationAttributeWidget;
    }

    /**
     * @param targetAssociationAttributeWidget the targetAssociationAttributeWidget to
     * set
     */
    @Override
    public void setTargetAssociationAttributeWidget(AssociationAttributeWidget targetAssociationAttributeWidget) {
        this.targetAssociationAttributeWidget = targetAssociationAttributeWidget;
    }

    @Override
    public IFlowElementWidget getTargetWidget() {
        return targetAssociationAttributeWidget;
    }
}