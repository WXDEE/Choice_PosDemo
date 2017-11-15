/**
 * Created by Knove on 2017/11/13.
 *  描述：制造桌子的工厂
 */
import React from 'react';
import { Card  } from 'antd';
import { connect } from 'react-redux'; // 引入connect
import { pointNowDesk,deskSearch} from '../../action/action';
const gridStyle = {
    width: '30px',
    background:'#919191',
    padding:'10px 34px 10px 28px',
    margin:5,
    color:'#fbfbfb',
    lineHeight:'5px',
    textAlign:'center',
    cursor:'pointer'

};

class DeskFactory extends React.Component{
    constructor(props) {
        super(props);
        this.state = {
        }
        this.nowDeskNumber=this.nowDeskNumber.bind(this);
        this.handleGetDeskAll=this.handleGetDeskAll.bind(this);


        this.handleGetDeskAll();
    }
     handleGetDeskAll(){
         const { deskSearch } = this.props;
         deskSearch();
     }
     nowDeskNumber(number){
         const { pointNowDesk } = this.props;
         pointNowDesk(number);
        console.log("现在指定的桌号为："+number);
    }
    render(){
        const desk=new Array();
         for(let i=0,len=this.props.nowAllDeskNumber;i<len;i++){
             desk.push(i);
         }
        let factory=null;
         if(this.props.nowDeskInfo!=null){
          let array=this.props.nowDeskInfo;
        console.log(array);

        factory =array.map( (item,index)=> {
           let number=index+1;
           if(item.deStatus!=0){
               return  <Card.Grid style={gridStyle} className="deskError" onClick={number=>this.nowDeskNumber(index+1)}>{number}</Card.Grid>
           }else
           return  <Card.Grid style={gridStyle} onClick={number=>this.nowDeskNumber(index+1)}>{number}</Card.Grid>
       }) }
        return(
            <Card noHovering bordered={false}>
            {factory}
            </Card>
        )
    }
}
const mapStateToProps  = (state) => {
           let count=0;  //全部桌子 的数量
    for(let i in state.httpData.deskInfo){
        count ++;
    }
    return { nowDeskNumber: state.httpData.deskNumber,
              nowDeskInfo:state.httpData.deskInfo,
              nowAllDeskNumber:count,
              orderState:state.httpData.orderState,

    };
}
//connect 实现， mapStateToProps将state传入props，参数2 将 action 作为 props 绑定到 MyComp 上
DeskFactory = connect(mapStateToProps , {pointNowDesk,deskSearch})(DeskFactory);

export default DeskFactory;
