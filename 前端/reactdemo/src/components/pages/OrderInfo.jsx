/**
 * Created by Knove on 2017/11/9.
 */
import React from 'react';
import { Button,Input,DatePicker ,Table,Icon,Modal,Carousel} from 'antd';
import { addNum ,orderInit,orderSearch,seeOrderDetails} from '../../action/action';
import { connect } from 'react-redux'; // 引入connect
import InfoTab from './InfoComponents/InfoTab';
const {RangePicker}=DatePicker;
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
            g_date=dateString[0];
            g_date1=dateString[1];
        }
        function onChange1(a,b,c) {
            console.log(a,b,c);
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
                        <RangePicker onChange={onChange} className="datePicker" />
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
                    {/*<Modal*/}
                        {/*className={"text-center"}*/}
                        {/*onCancel={this.handleCancel}*/}
                        {/*visible={this.state.visible}*/}
                        {/*title={"雄鹰订餐系统使用指南"}*/}
                        {/*footer={<Button type="primary" onClick={this.handleCancel}>关闭</Button>}*/}
                    {/*>*/}
                        {/*<Carousel afterChange={onChange1}>*/}
                            {/*<div>*/}
                                {/*<p><b>使用步骤说明</b></p>*/}
                                {/*<p className={"text-left1"}>*/}
                                    {/*<p>1、选择可使用餐桌</p>*/}
                                    {/*<p>2、选择未售完菜品，可对菜品进行数量、删除操作</p>*/}
                                    {/*<p>3、提交订单</p>*/}
                                    {/*<p>4、对已上菜品进行上菜标记</p>*/}
                                    {/*<p>5、结账</p>*/}
                                {/*</p>*/}

                            {/*</div>*/}
                            {/*<div>*/}
                                {/*<p><b>各区域详细说明</b></p>*/}
                                {/*<p className={"text-left"}><b>选桌区：</b>显示所有餐桌的使用情况。红色表示餐桌正在使用，灰色表示餐桌未使用，绿色表示餐桌正在点单。</p>*/}
                                {/*<p className={"text-left"}><b>售空菜品区：</b>显示当前已售空的所有菜品。</p>*/}
                                {/*<p className={"text-left"}><b>选餐区：</b>显示餐厅提供的所有菜品。灰色表示菜品可选，红色表示菜品已售空。</p>*/}
                                {/*<p className={"text-left"}><b>订单明细区：</b>显示当前订单的信息。未提交订单前可删除菜品，提交订单后，菜品不可删除；*/}
                                    {/*上菜后，点击上菜表示菜品已上；点击结账后，此餐桌处于可使用状态。</p>*/}
                            {/*</div>*/}
                        {/*</Carousel>*/}
                    {/*</Modal>*/}
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


