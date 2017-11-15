/**
 * Created by Knove on 2017/11/13.
 *  描述：制造菜品的工厂
 */
import React from 'react';
import { Card ,Spin ,Modal } from 'antd';
import { connect } from 'react-redux'; // 引入connect
import { addFoodDetails,pointNowDesk,foodInit} from '../../action/action';
function info() {
    Modal.info({
        title: 'This is a notification message',
        content: (
            <div>
                <p>some messages...some messages...</p>
                <p>some messages...some messages...</p>
            </div>
        ),
        onOk() {},
    });
}
function warning() {
    Modal.warning({
        title: '注意',
        content: '请选择桌号！',
    });
}
function error() {
    Modal.error({
        title: '抱歉',
        content: '本菜品已经售空！',
    });
}
let loading=true;
class FoodFactory extends React.Component{


    constructor(props) {
        super(props);
        this.state = {
        }
        this.addFoods=this.addFoods.bind(this);
        this.nowDeskNumber=this.nowDeskNumber.bind(this);

        const {foodInit}=this.props;
        foodInit();
    }
    addFoods(index,nowDeskNum,nowFoodNum,foodName,foodPrice,foodNum){
        const { addFoodDetails } = this.props;
        console.log(nowDeskNum);
        if(nowDeskNum==null){
            console.log("请选择桌号");
            warning();
        }else{
        addFoodDetails(index,nowDeskNum,nowFoodNum,foodName,foodPrice,foodNum);
        console.log("给"+nowDeskNum+"桌添加菜品！"+foodName);
        }
    }

    nowDeskNumber(number){
        const { pointNowDesk } = this.props;
        pointNowDesk(number);
        console.log("现在指定的桌号为："+number);
    }
    render(){
        let nowDeskNum=this.props.nowDeskNumber;
        let nowFoodNum=this.props.getDeskFoodArraynum;
        let factory=null;
        if(this.props.loading)loading=false;

        if(this.props.foodMain!=null){


        factory =this.props.foodMain.map( (item,index) =>{

            if(item.dcId==this.props.fid){
           /* if(item.dCount>0&&item.dCount<50) return  <Card.Grid className="foodButton normalFood edFood">{item.dName} {item.dPrice}¥</Card.Grid>
            else*/ if(item.dCount==0) return  <Card.Grid className="foodButton normalFood lackFood" onClick={error}>{item.dName} {item.dPrice}¥</Card.Grid>
            else return  <Card.Grid className="foodButton normalFood"
                                    onClick={index=>this.addFoods(item.id,nowDeskNum,nowFoodNum,item.dName,item.dPrice,item.dCount)}>
                    {item.dName} {item.dPrice}¥
                </Card.Grid>
            }
       })  }
        return(
            <Spin size="large" spinning={loading} >
            <Card noHovering bordered={false} className="factoryFood">

            {factory}
            </Card>
            </Spin>
        )
    }
}
const mapStateToProps  = (state) => {
    let nowDeskNumber= state.httpData.deskNumber;
    if(nowDeskNumber==null)nowDeskNumber=0;
    return { nowDeskNumber: state.httpData.deskNumber,
        getDeskFoodArraynum:state.httpData.deskTable[nowDeskNumber].foodArray.length,
        foodMain:state.httpData.foodTable,
        loading:state.httpData.foodSuccess,

    };
}
//connect 实现， mapStateToProps将state传入props，参数2 将 action 作为 props 绑定到 MyComp 上
FoodFactory = connect(mapStateToProps , {addFoodDetails,pointNowDesk,foodInit})(FoodFactory);

export default FoodFactory;
