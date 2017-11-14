/**
 * Created by Knove on 2017/11/9.
 */
import React from 'react';
import { Button,Input,DatePicker ,Table,Icon,Modal} from 'antd';
import { addNum ,orderInit,orderSearch} from '../../action/action';
import { connect } from 'react-redux'; // 引入connect
import InfoTab from './InfoComponents/InfoTab';
let g_date;
let g_date1;

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

    showModal (){
        this.setState({
            visible: true,
        });
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
        let data=[this.state.value,g_date,g_date1];
        const {orderSearch}=this.props;
        orderSearch(data);
    }
    render(){

        const theNumber = this.props.dataA;

        //表格状态
        let loading=true;
        if(this.props.loading)loading=false;

        function onChange(date, dateString) {
            g_date=dateString;
        }
        function onChange1(date, dateString) {
            g_date1=dateString;
        }

        const columns = [{
            title: '订单编号',
            dataIndex: 'ID',
            key: 'id',
        }, {
            title: '下单时间',
            dataIndex: 'Time',
            key: 'time',
        }, {
            title: '菜品数量',
            dataIndex: 'FoodNum',
            key: 'foodnum',
        },  {
            title: '桌号',
            dataIndex: 'DeskID',
            key: 'deskID',
        }, {
            title: '消费金额',
            dataIndex: 'Money',
            key: 'money',
            render: text => <span>{text}¥</span>,
        }, {
            title: '订单状态',
            dataIndex: 'State',
            key: 'state',
        }, {
            title: '操作',
            dataIndex: 'Func',
            key: 'func',
            render: text => <a  onClick={this.showModal}>查看明细</a>,
        }
    ];

        const listColumns = [{
            title: '菜品名',
            dataIndex: 'FoodName',
            key: 'name',
        }, {
            title: '数量(份)',
            dataIndex: 'Num',
            key: 'num',
        }, {
            title: '单价',
            dataIndex: 'Price',
            key: 'price',
        }
        ];

        const listData = [{
            key: '1',
            FoodName: '糖醋里脊',
            Num: '1',
            Price: '200',
            render:text=><spam>text¥</spam>
        },{
            key: '2',
            FoodName: '油炸冰淇淋',
            Num: '2',
            Price: '170',
            render:text=><spam>text¥</spam>
        },{
            key: '3',
            FoodName: '糖醋里脊',
            Num: '1',
            Price: '200',
            render:text=><spam>text¥</spam>
        }];

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
                    <div className="normalInput"><Button type="primary" onClick={this.handleSearch}>查询</Button><Button type="primary" >清空</Button></div>

                </section>
                <div className="tableMain">
                    <span className="tableDate">营业额：<span className="tableMoney">250,000.00¥</span></span>
                    <span className="tableDate">| </span>
                    <span className="tableDate">订单量：<span className="tableMoney">50,000.00笔</span></span>
                    <Table
                        columns={columns}
                        dataSource={this.props.mainTable}
                        className=""
                        size="small"
                        loading={loading}
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
                               <td>099287</td>
                           </tr>
                           <tr>
                               <td>桌号</td>
                               <td>022</td>
                           </tr>
                           <tr>
                               <td>下单时间</td>
                               <td>2017-11-11 10:00</td>
                           </tr>
                           </tbody>
                       </table>
                        <hr  className="doLine" />
                        <section className="">
                            <Table
                                columns={listColumns}
                                dataSource={listData}
                                pagination={false}
                                className="listInfo"
                                size="small"
                            />
                        </section>
                        <hr  className="doLine" />
                        <div style={{marginLeft:'10%'}}>共计金额：570¥</div>
                    </Modal>
                </div>


            </div>
        )
    }

}
const mapStateToProps  = (state) => {
    return { dataA: state.httpData.theNumber,
        mainTable:state.httpData.orderTable,//表格数据
        loading:state.httpData.ok, //表格是否加载完毕
    };
}
//connect 实现， mapStateToProps将state传入props，参数2 将 action 作为 props 绑定到 MyComp 上
OrderInfo = connect(mapStateToProps , {addNum,orderInit,orderSearch})(OrderInfo);
export default OrderInfo;


