/**
 * Created by Knove on 2017/11/9.
 */
import React from 'react';
import InfoTab from './InfoComponents/InfoTab';
import {Table, Icon, DatePicker, Input, Button, Modal, Form} from 'antd';

const FormItem = Form.Item;

const InfoForm = Form.create()(
    props => {
        const {visible, onCancel, onOk, form} = props;
        const {getFieldDecorator} = form;
        return (
            <Modal
                onOk={onOk}
                onCancel={onCancel}
                visible={visible}
                title={"添加菜品"}
            >
                <Form layout={"horizontal"}>
                    <FormItem label={"菜品名称"} layout={"horizontal"}>
                        {getFieldDecorator("name", {
                            rules:[{required:true, message:"菜品名称不能为空"}]
                        })(<Input/>)}
                    </FormItem>
                    <FormItem label={"菜品单价"}>
                        {getFieldDecorator("type", {
                            rules:[{required:true, message:"菜品单价不能为空"}]
                        })(<Input/>)}
                    </FormItem>
                    <FormItem label={"菜品余量"}>
                        {getFieldDecorator("num", {
                            rules:[{required:true, message:"菜品余量不能为空"}]
                        })(<Input/>)}
                    </FormItem>
                    <FormItem label={"汉拼首字母"}>
                        {getFieldDecorator("initial", {
                            rules:[{required:true, message:"汉拼首字母不能为空"}]
                        })(<Input/>)}
                    </FormItem>
                    <FormItem label={"所属类别"}>
                        {getFieldDecorator("type", {
                            rules:[{required:true, message:"所属类别不能为空"}]
                        })(<Input/>)}
                    </FormItem>
                    <FormItem label={"原料"}>
                        {getFieldDecorator("material")(<textarea/>)}
                    </FormItem>
                    <FormItem label={"备注"}>
                        {getFieldDecorator("remark")(<textarea/>)}
                    </FormItem>
                </Form>
            </Modal>
        )
    }
)


class FoodInfo extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            visible: false,
            visible1:false
        };
        this.showModal = this.showModal.bind(this);
        this.handleOk = this.handleOk.bind(this);
        this.handleCancel = this.handleCancel.bind(this);
        this.saveFormRef = this.saveFormRef.bind(this);

        this.showModal1 = this.showModal1.bind(this);
        this.handleOk1 = this.handleOk1.bind(this);
        this.handleCancel1 = this.handleCancel1.bind(this);
    }
    //添加菜品
    showModal() {
        this.setState({
            visible: true,
        })
    }
    saveFormRef(form){
        this.form=form;
    }
    handleOk(e) {
        const form=this.form;
        form.validateFields((err,value)=>{
            if(err)
                return;
            form.resetFields();
            this.setState({
                visible: false,
            })
        });
    }
    handleCancel(e) {
        console.log(e);
        this.setState({
            visible: false,
        })
    }
    //修改菜品信息
    showModal1() {
        this.setState({
            visible1: true,
        })
    }
    handleOk1(e) {
        console.log(e);
        this.setState({
            visible1: false,
        })
    }
    handleCancel1(e) {
        console.log(e);
        this.setState({
            visible1: false,
        })
    }

    render() {
        function onChange(date, dateString) {
            console.log(date, dateString);
        }

        const data = [{
            key: 1,
            name: '麻婆豆腐',
            price: '￥10',
            num: 0,
            date: "2017-11-11",
            initial: 'MPDF',
            type: '素菜',
            material: '麻婆、豆腐',
            remark: '对辣椒过敏者慎食'
        }, {
            key: 2,
            name: '麻婆豆腐',
            price: '￥10',
            num: 0,
            date: "2017-11-11",
            initial: 'MPDF',
            type: '素菜',
            material: '麻婆、豆腐',
            remark: '对辣椒过敏者慎食'
        }, {
            key: 3,
            name: '麻婆豆腐',
            price: '￥10',
            num: 0,
            date: "2017-11-11",
            initial: 'MPDF',
            type: '素菜',
            material: '麻婆、豆腐',
            remark: '对辣椒过敏者慎食'
        }, {
            key: 4,
            name: '麻婆豆腐',
            price: '￥10',
            num: 0,
            date: "2017-11-11",
            initial: 'MPDF',
            type: '素菜',
            material: '麻婆、豆腐',
            remark: '对辣椒过敏者慎食'
        }, {
            key: 5,
            name: '麻婆豆腐',
            price: '￥10',
            num: 0,
            date: "2017-11-11",
            initial: 'MPDF',
            type: '素菜',
            material: '麻婆、豆腐',
            remark: '对辣椒过敏者慎食'
        }];
        const columns = [{
            title: '菜品名称',
            dataIndex: 'name'
        }, {
            title: '菜品单价',
            dataIndex: 'price'
        }, {
            title: '菜品余量',
            dataIndex: 'num'
        }, {
            title: '上架日期',
            dataIndex: 'date'
        }, {
            title: '汉拼首字母',
            dataIndex: 'initial'
        }, {
            title: '所属类别',
            dataIndex: 'type'
        }, {
            title: '原料',
            dataIndex: 'material'
        }, {
            title: '备注',
            dataIndex: 'remark'
        }, {
            title: '操作',
            dataIndex: 'opera',
            render: text =>
                <span>
                    <a onClick={this.showModal1}>编辑</a>
                    <span className="ant-divider"/>
                    <a >删除</a>
                </span>

        }];
        return (
            <div>
                <InfoTab infoNum="2"/>
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
                        <Button type="primary" onClick={this.showModal}>添加菜品</Button>
                    </div>
                </div>
                <div className="tableMain clearFix">
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
                    <InfoForm
                        ref={this.saveFormRef}
                        visible={this.state.visible}
                        onCancel={this.handleCancel}
                        onOk={this.handleOk}
                    />
                    <Modal
                        title="编辑菜品信息"
                        visible={this.state.visible1}
                        onCancel={this.handleCancel1}
                        onOk={this.handleOk1}
                        footer={[
                            <Button type="primary" onClick={this.handleCancel1}>取消</Button>,
                            <Button type="primary" onClick={this.handleOk1}>确定</Button>
                        ]}
                    >
                        <div className="editInfo">
                            <div>菜品名称： 麻婆豆腐</div>
                            <div>菜品单价：<input type="text" defaultValue="￥10"></input></div>
                            <div>菜品余量：<input type="text" defaultValue="0"></input></div>
                            <div className="clearFix">
                                <span>菜品原料：</span>
                                <textarea defaultValue="麻婆、豆腐"></textarea>
                            </div>
                            <div className="clearFix">
                                <span>菜品备注：</span>
                                <textarea defaultValue="对辣椒过敏者慎食"></textarea>
                            </div>
                        </div>

                    </Modal>

                </div>
            </div>
        )
    }
}

export default FoodInfo;
