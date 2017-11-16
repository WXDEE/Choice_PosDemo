/**
 * Created by Knove on 2017/11/9.
 */
import React from 'react';
import { Button,Input,DatePicker ,Table,Icon,Modal} from 'antd';
import { addNum ,orderInit,orderSearch,seeOrderDetails} from '../../action/action';
import { connect } from 'react-redux'; // 引入connect
import InfoTab from './InfoComponents/InfoTab';
let g_date;
let g_date1;
//表格状态
//////orderid
let loading=true;
let loading1=true;
class OrderInfo extends React.Component{

    constructor(props) {
        super(props);
        this.state = {
            visible: false,
            value:"",
        }
       this.add=this.add.bind(this);
        this.showModal=this.showModal.bind(this);
        this.handleCancel=this.handleCancel.bind(this);
        this.handleChange=this.handleChange.bind(this);
        this.handleSearch=this.handleSearch.bind(this);
        const { orderInit } = this.props;
        orderInit();
    }
    add() {
        const { addNum } = this.props;
        addNum();
    }

    showModal (id){
        this.setState({
            visible: true,
        });
        const {seeOrderDetails}=this.props;
        seeOrderDetails(id);
    }
    handleCancel (e) {
        console.log(e);
        this.setState({
            visible: false,
        });
    }
    //修改value值
    handleChange(e){
        this.setState({
            value:e.target.value,
        })
    }
    handleSearch(e){
        e.preventDefault();
        let data={
            dName:this.state.value,
            sdDate:g_date,
            edDate:g_date1};
        const {orderSearch}=this.props;
        orderSearch(data);
    }
    render(){

        const theNumber = this.props.dataA;


        if(this.props.loading)loading=false;
        if(this.props.loading1)loading1=false;

        function onChange(date, dateString) {
            g_date=dateString;
        }
        function onChange1(date, dateString) {
            g_date1=dateString;
        }

        const columns = [{
            title: '订单编号',
            dataIndex: 'oNum',
            key: 'id',
        }, {
            title: '下单时间',
            dataIndex: 'oDate',
            key: 'time',
        }, {
            title: '菜品数量',
            dataIndex: 'odCount',
            key: 'foodnum',
        },  {
            title: '桌号',
            dataIndex: 'deId',
            key: 'deskID',
        }, {
            title: '消费金额',
            dataIndex: 'oTotal',
            key: 'money',
            render: text => <span>¥{text}</span>,
        }, {
            title: '订单状态',
            dataIndex: 'oStatus',
            key: 'state',
        }, {
            title: '操作',
            dataIndex: 'Func',
            key: 'func',
            render: (text,record,index) => <a onClick={()=>this.showModal(record.id)}>查看明细</a>,
        }
    ];

        const listColumns = [{
            title: '菜品名',
            dataIndex: 'dId',
            key: 'name',
        }, {
            title: '数量(份)',
            dataIndex: 'oiCount',
            key: 'num',
        }, {
            title: '上菜状态',
            dataIndex: 'oiStatus',
            key: 'price',
        }
        ];

        return(
            <div>
         {/*       OrderInfo --
                <span>{theNumber}</span>
                <Button type="primary" onClick={this.add}>Add</Button>
                <div className="demo">
                    后台运维页面-订单明细
                </div>*/}
                <InfoTab infoNum="1" />
                <section className="funcTitle">
                    <div className="normalInput">订单编号 <Input  className="datePicker" value={this.state.value} onChange={this.handleChange}/></div>
                    <div className="normalInput">下单时间
                        <DatePicker onChange={onChange} className="datePicker" />
                        至<DatePicker onChange={onChange1} className="datePicker" />
                    </div>
                    <div className="normalInput">
                        <Button type="primary" icon="search" onClick={this.handleSearch}>查询</Button>
                    </div>

                </section>
                <div className="tableMain">
                    <span className="tableDate">营业额：<span className="tableMoney">¥{this.props.OrderPriceSum}</span></span>
                    <span className="tableDate">| </span>
                    <span className="tableDate">订单量：<span className="tableMoney">{this.props.OrderSum}笔</span></span>
                    <Table
                        columns={columns}
                        dataSource={this.props.mainTable}
                        className=""
                        size="small"
                        loading={loading}
                        pagination={{pageSize:5}}
                    />
                    <Modal
                        title="订单明细"
                        visible={this.state.visible}
                        onCancel={this.handleCancel}
                        footer={<Button type="primary" onClick={this.handleCancel}>关闭</Button>}
                    >
                       <table className="listInfo">
                           <tbody>
                           <tr>
                               <td>订单号</td>
                               <td>{this.props.orderDetail.oNum}</td>
                           </tr>
                           <tr>
                               <td>桌号</td>
                               <td>{this.props.orderDetail.deId}</td>
                           </tr>
                           <tr>
                               <td>下单时间</td>
                               <td>{this.props.orderDetail.oDate}</td>
                           </tr>
                           </tbody>
                       </table>
                        <hr  className="doLine" />
                        <section className="">
                            <Table
                                columns={listColumns}
                                dataSource={this.props.orderDetailTable}
                                pagination={false}
                                className="listInfo"
                                size="small"
                                loading={loading1}
                            />
                        </section>
                        <hr  className="doLine" />
                        <div style={{marginLeft:'10%'}}>共计金额：¥{this.props.orderDetail.oTotal}</div>
                    </Modal>
                </div>


            </div>
        )
    }

}
const mapStateToProps  = (state) => {
  //获取订单总数
    let count=0;
    let sumPrice=0;
    let array={};
    let data={};
    for(let i in state.httpData.orderTable){
               count ++;
        sumPrice+=Number(state.httpData.orderTable[i].oTotal);
            }

    if(state.httpData.orderDetailsTable!=null){
        array=state.httpData.orderDetailsTable.orderItemList;
        data=state.httpData.orderDetailsTable;
    }



    return { dataA: state.httpData.theNumber,
        mainTable:state.httpData.orderTable,//表格数据
        loading:state.httpData.success, //表格是否加载完毕
        OrderSum:count,
        OrderPriceSum:sumPrice,
        orderDetail:data,
        orderDetailTable:array, //订单详情表格
        loading1:state.httpData.success1,
    };
}
//connect 实现， mapStateToProps将state传入props，参数2 将 action 作为 props 绑定到 MyComp 上
OrderInfo = connect(mapStateToProps , {addNum,orderInit,orderSearch,seeOrderDetails})(OrderInfo);
export default OrderInfo;


