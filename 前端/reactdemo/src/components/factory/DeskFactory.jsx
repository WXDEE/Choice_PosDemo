/**
 * Created by Knove on 2017/11/13.
 *  描述：制造桌子的工厂
 */
import React from 'react';
import { Card  } from 'antd';
import { connect } from 'react-redux'; // 引入connect
import { pointNowDesk} from '../../action/action';
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
    constructor(props) {
        super(props);
        this.state = {
        }
        this.nowDeskNumber=this.nowDeskNumber.bind(this);
    }

     nowDeskNumber(number){
         const { pointNowDesk } = this.props;
         pointNowDesk(number);

        console.log("现在指定的桌号为："+number);
    }
    render(){
        const desk=[0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15];
       let factory =desk.map( (item,index)=> {
           let number=item+1;
           if(number==4||number==9||number==11){
               return  <Card.Grid style={gridStyle} className="deskError" onClick={number=>this.nowDeskNumber(index+1)}>{number}</Card.Grid>
           }else
           return  <Card.Grid style={gridStyle} onClick={number=>this.nowDeskNumber(index+1)}>{number}</Card.Grid>
       })
        return(
            <Card noHovering bordered={false}>
            {factory}
            </Card>
        )
    }
}
const mapStateToProps  = (state) => {
    return { nowDeskNumber: state.httpData.deskNumber,

    };
}
//connect 实现， mapStateToProps将state传入props，参数2 将 action 作为 props 绑定到 MyComp 上
DeskFactory = connect(mapStateToProps , {pointNowDesk})(DeskFactory);

export default DeskFactory;
