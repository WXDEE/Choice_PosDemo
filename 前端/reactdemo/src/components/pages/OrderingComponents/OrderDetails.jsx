/**
 * Created by Knove on 2017/11/13.
 *  描述：服务员订餐页面的订单明细选项卡
 */
import React from 'react';
import { Card,Table,Button } from 'antd';
class SelectFood extends React.Component{

    render(){
        let ScreenHeight=document.body.clientHeight-115; //获取 全屏幕减去title的高度
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
            <div >
                <Card title="订单明细"   bodyStyle={{ width: '100%',height:ScreenHeight }} className="orderDetails">
                    <table className="listInfoOrdering titleTable">
                        <tbody>
                        <tr className="listTableImportant">
                            <td >桌号</td>
                            <td>022</td>
                        </tr>
                        <tr>
                            <td>订单号</td>
                            <td>099287</td>
                        </tr>
                        <tr>
                            <td>下单时间</td>
                            <td>2017-11-11 10:00</td>
                        </tr>
                        </tbody>
                    </table>
                    <hr  className="doLineOrdering" />

                        <Table
                            columns={listColumns}
                            dataSource={listData}
                            pagination={false}
                            className="listInfoOrdering infoTable"
                            size="small"
                        />
                    <hr  className="doLineOrdering" />
                <section className="doCenter">
                    <span style={{fontSize:14}} >共计金额：570¥ </span><br />
                    <span style={{fontSize:14}} > 点餐量：10份</span>


                </section>
                    <section className="detailsButton">
                        <Button type="primary" size="large">提交订单</Button>
                        <Button type="primary"size="large">取消订单</Button>
                    </section>
                </Card>
            </div>
        )
    }
}
export default SelectFood;
