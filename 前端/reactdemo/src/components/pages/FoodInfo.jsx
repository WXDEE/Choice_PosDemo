/**
 * Created by Knove on 2017/11/9.
 */
import React from 'react';
import InfoTab from './InfoComponents/InfoTab';
import {Table,Icon,DatePicker,Input,Button} from 'antd';

class FoodInfo extends React.Component{
    constructor(props){
        super(props);
        this.state={
        }
    }
    render(){
        function onChange(date, dateString) {
            console.log(date, dateString);
        }
        const data=[{
            key:1,
            name:'麻婆豆腐',
            price:'￥10',
            num:0,
            date:"2017-11-11",
            initial:'MPDF',
            type:'素菜',
            material:'麻婆、豆腐',
            remark:'对辣椒过敏者慎食'
        },{
            key:2,
            name:'麻婆豆腐',
            price:'￥10',
            num:0,
            date:"2017-11-11",
            initial:'MPDF',
            type:'素菜',
            material:'麻婆、豆腐',
            remark:'对辣椒过敏者慎食'
        },{
            key:3,
            name:'麻婆豆腐',
            price:'￥10',
            num:0,
            date:"2017-11-11",
            initial:'MPDF',
            type:'素菜',
            material:'麻婆、豆腐',
            remark:'对辣椒过敏者慎食'
        },{
            key:4,
            name:'麻婆豆腐',
            price:'￥10',
            num:0,
            date:"2017-11-11",
            initial:'MPDF',
            type:'素菜',
            material:'麻婆、豆腐',
            remark:'对辣椒过敏者慎食'
        },{
            key:5,
            name:'麻婆豆腐',
            price:'￥10',
            num:0,
            date:"2017-11-11",
            initial:'MPDF',
            type:'素菜',
            material:'麻婆、豆腐',
            remark:'对辣椒过敏者慎食'
        }];
        const columns=[{
            title:'菜品名称',
            dataIndex:'name'
        },{
            title:'菜品单价',
            dataIndex:'price'
        },{
            title:'菜品余量',
            dataIndex:'num'
        },{
            title:'上架日期',
            dataIndex:'date'
        },{
            title:'汉拼首字母',
            dataIndex:'initial'
        },{
            title:'所属类别',
            dataIndex:'type'
        },{
            title:'原料',
            dataIndex:'material'
        },{
            title:'备注',
            dataIndex:'remark'
        },{
            title:'操作',
            dataIndex:'opera',
            render:text=>
                <span>
                    <a href="#">编辑</a>
                    <span className="ant-divider" />
                    <a href="#">删除</a>
                </span>

        }];
        return(
            <div >
                <InfoTab infoNum="2" />
                <div className="funcTitle">
                    <div className="smallInput">
                        菜品名称<Input className="datePicker"/>
                    </div>
                    <div className="smallInput">
                        上架时间<DatePicker onChange={onChange} className="datePicker"/>
                        至<DatePicker onChange={onChange} className="datePicker"/>
                    </div>
                    <div className="smallInput">
                        <Button type="primary">查询</Button>
                    </div>
                    <div className="smallInput">
                        <Button type="primary">刷新</Button>
                    </div>
                    <div>
                        <Button type="primary">添加菜品</Button>
                    </div>
                </div>
                <div className="tableMain clearfix">
                    <span className="tableDate">菜品种类数：<span className="tableMoney">50种</span></span>
                    <span className="tableDate">|</span>
                    <span className="tableDate">售空菜品数：<span className="tableMoney">5种</span></span>
                    <span className="tableDate">|</span>
                    <span className="tableDate">余量不足菜品数：<span className="tableMoney">10种</span></span>
                    <span className="tableDate">余量不足菜品数：<span className="tableMoney">10种</span></span>
                    <span className="tableRight">
                       <div><span className="redLine"></span>已售空菜品</div>
                        <div><span className="yellowLine"></span>余量不足菜品</div>
                    </span>
                    <Table dataSource={data} columns={columns} size={"small"}/>
                </div>
            </div>
        )
    }
}
export default FoodInfo;
