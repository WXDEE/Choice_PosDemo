/**
 * Created by Knove on 2017/11/13.
 *  描述：服务员订餐页面的订单明细选项卡
 */
import React from 'react';
import { Card,Table,Button,InputNumber,Modal } from 'antd';
import { connect } from 'react-redux'; // 引入connect
import { deleteFoodDetails,numberFoodDetails,pushOrder,endOrder,pointNowDesk} from '../../../action/action';


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
function success(text) {
    Modal.success({
        title: '成功！',
        content:text ,
    });
}

let g_key=1;
let foodNum=0;
let sumPrice=0;
let g_stats=0;  //状态   0为 购物车  1 为订单明细
class SelectFood extends React.Component{
    constructor(props) {
        super(props);
        this.state = {
        }
        this.doDelete=this.doDelete.bind(this);
        this.onChange=this.onChange.bind(this);
        this.summitOrder=this.summitOrder.bind(this);
        this.endOrder=this.endOrder.bind(this);
    }
    nowDeskNumber(number){
        const { pointNowDesk } = this.props;
        pointNowDesk(number);
        console.log("现在指定的桌号为："+number);
    }

    doDelete(record,index,event){
        if(null!=event)g_key=record.key;

        const { deleteFoodDetails,numberFoodDetails } = this.props;
        if(index=="change"){
            numberFoodDetails(this.props.nowDeskNumber,g_key,record);
        }
        else if(null!=record&&event.target.tagName=="A"){
            if(event.target.innerHTML=="删除")
              deleteFoodDetails(this.props.nowDeskNumber,record.key);
            else if(event.target.innerHTML=="上菜"){
                console.log(record);
            }
        }
    }
    onChange(value) {
        console.log('菜品改变数量了：', value);
        this.doDelete(value,"change");
    }
    summitOrder(){
       let data ={
           deId:this.props.nowDeskNumber,
           oTotal:sumPrice,
           odCount:foodNum,
           orderItemList:[
           ],
       }
       for(let i=0;i<this.props.getDeskFoodArray.length;i++){
           data.orderItemList.push({
               dId:this.props.getDeskFoodArray[i].FoodID,
               oiCount:this.props.getDeskFoodArray[i].nowNum,
           });
       }
        const { pushOrder } = this.props;
        pushOrder(data);
        success('您已成功提交订单！');
    }
   endOrder(orderNumber){
       const { endOrder } = this.props;
       endOrder(orderNumber,this.props.nowDeskNumber);
       success('您已成功结账！');
       //指定目前桌号为空
       this.nowDeskNumber(null);
       //删除储存的信息

   }
    render(){
        let ScreenHeight=document.body.clientHeight-104; //获取 全屏幕减去title的高度
        let deskNumber=this.props.nowDeskNumber;
        let orderNumber='';
        let orderTime='';
        let orderId=''
       for(let i=0,index=this.props.getDeskFoodArray.length;i<index;i++){
           if(null!=this.props.getDeskFoodArray[i]){
           sumPrice+=Number(this.props.getDeskFoodArray[i].Price*this.props.getDeskFoodArray[i].nowNum);
               foodNum+=Number(this.props.getDeskFoodArray[i].nowNum);
           console.log(this.props.getDeskFoodArray[i].Price);
           }
       }
       if(this.props.orderState!=null){
                  g_stats=0;  //初始化状态为购物车
           for(let i=0,index=this.props.orderState.length;i<index;i++){
               if(deskNumber==this.props.orderState[i].deskNum){
                   g_stats=1;   //状态为订单明细
                   orderId=this.props.orderState[i].deskInfo.data.id;
                   orderNumber=this.props.orderState[i].deskInfo.data.oNum;
                   orderTime=this.props.orderState[i].deskInfo.data.oDate;
               }
           }
       }

        let listColumns = [{
            title: '菜品名',
            dataIndex: 'FoodName',
            key: 'name',
        }, {
            title: '数量(份)',
            dataIndex: 'nowNum',
            key: 'nownum',
            render:text=><InputNumber min={1} max={100} defaultValue={text} onChange={this.onChange} />
        }, {
            title: '单价',
            dataIndex: 'Price',
            key: 'price',
            render:text=><span>{text}¥</span>
        }, {
                title: '操作',
                dataIndex: 'func',
                key: 'func',
            render:text=><a>删除</a>
            }
        ];
       if( g_stats==1){
           listColumns = [{
               title: '菜品名',
               dataIndex: 'FoodName',
               key: 'name',
           }, {
               title: '数量(份)',
               dataIndex: 'nowNum',
               key: 'nownum',
           }, {
               title: '单价',
               dataIndex: 'Price',
               key: 'price',
               render:text=><span>{text}¥</span>
           }, {
               title: '操作',
               dataIndex: 'func',
               key: 'func',
               render:text=> <a>{text}</a>

           }
           ];
       }
       let dataArray=this.props.getDeskFoodArray;
       if(g_stats==1) {
           dataArray= this.props.afterEndFoodArray;
           console.log(dataArray);
       }
        return(
            <div >
                <Card title="订单明细"   bodyStyle={{ width: '100%',height:ScreenHeight }} className="orderDetails">
                    <table className="listInfoOrdering titleTable">
                        <tbody>
                        <tr className="listTableImportant">
                            <td >桌号</td>
                            <td>{deskNumber}</td>
                        </tr>
                        <tr>
                            <td>订单号</td>
                            <td>{orderNumber}</td>
                        </tr>
                        <tr>
                            <td>下单时间</td>
                            <td>{orderTime}</td>
                        </tr>
                        </tbody>
                    </table>
                    <hr  className="doLineOrdering" />
                    <div className="heightDo">
                        <Table
                            columns={listColumns}
                            dataSource={dataArray}
                            pagination={false}
                            className="listInfoOrdering infoTable"
                            size="small"
                            onRowClick={(record,index,event)=>this.doDelete(record,index,event)}
                        />
                    </div>
                    <hr  className="doLineOrdering" />
                <section className="doCenter">
                    <span style={{fontSize:14}} >共计金额：{sumPrice}¥ </span><br />
                    <span style={{fontSize:14}} > 点餐量：{foodNum}份</span>


                </section>
                    <section className="detailsButton">
                        <Button type="primary" size="large" onClick={this.summitOrder} >提交订单</Button>
                        <Button type="primary"size="large" onClick={()=>this.endOrder(orderId)}>结账</Button>
                    </section>
                </Card>
            </div>
        )
    }
}
const mapStateToProps  = (state) => {
    foodNum=0;
     sumPrice=0;
    let nowDeskNumber= state.httpData.deskNumber;

    let afterEndFoodArray={};
    if(nowDeskNumber==null)nowDeskNumber=0;
    if(state.httpData.orderState!=null){
        for(let i=0,index=state.httpData.orderState.length;i<index;i++){
            if(state.httpData.deskNumber==state.httpData.orderState[i].deskNum){
                afterEndFoodArray=state.httpData.orderState[i].data;
            }
        }
    }
    console.log(afterEndFoodArray);
    return { nowDeskNumber: state.httpData.deskNumber,
              getDeskFoodArray:state.httpData.deskTable[nowDeskNumber].foodArray,
              orderState:state.httpData.orderState,
              afterEndFoodArray:afterEndFoodArray
    };
}
//connect 实现， mapStateToProps将state传入props，参数2 将 action 作为 props 绑定到 MyComp 上
SelectFood = connect(mapStateToProps,{deleteFoodDetails,numberFoodDetails,pushOrder,endOrder,pointNowDesk})(SelectFood);


export default SelectFood;
