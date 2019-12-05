package ly.com.vertex.sample1303;

import ly.com.base.utils.MatrixState;

/**
 * @author：ly on 2019-12-05 09:18
 * @mail：liuyan@zhimei.ai
 */
public class TreeTrunkControl {
    static int num=0;
    //标志
    int flag=0;
    //树的位置
    float positionX;
    float positionY;
    float positionZ;
    //树干的模型
    TreeTrunk treeTrunk;
    public TreeTrunkControl(float positionX,float positionY,float positionZ,TreeTrunk treeTrunk)
    {
        flag=num++;
        this.positionX=positionX;
        this.positionY=positionY;
        this.positionZ=positionZ;
        this.treeTrunk=treeTrunk;
    }
    public void drawSelf(int tex_treejointId,float bend_R,float wind_direction)
    {
        MatrixState.pushMatrix();
        MatrixState.translate(positionX, positionY, positionZ);//移动到指定的位置
        treeTrunk.drawSelf(tex_treejointId, bend_R, wind_direction);
        MatrixState.popMatrix();
    }
}
