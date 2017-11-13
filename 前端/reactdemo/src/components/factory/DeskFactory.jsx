/**
 * Created by Knove on 2017/11/13.
 *  描述：制造桌子的工厂
 */
import React from 'react';
import { Card  } from 'antd';
const gridStyle = {
    width: '30px',
    background:'#919191',
    padding:'10px 34px 10px 28px',
    margin:5,
    color:'#fbfbfb',
    lineHeight:'5px',
    textAlign:'center'

};
class DeskFactory extends React.Component{

    render(){
        const desk=[0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15];
       let factory =desk.map(function (item) {
           let number=item+1;
           if(number==4||number==9||number==11){
               return  <Card.Grid style={gridStyle} className="deskError">{number}</Card.Grid>
           }else
           return  <Card.Grid style={gridStyle}>{number}</Card.Grid>
       })
        return(
            <Card noHovering bordered={false}>
            {factory}
            </Card>
        )
    }
}
export default DeskFactory;
