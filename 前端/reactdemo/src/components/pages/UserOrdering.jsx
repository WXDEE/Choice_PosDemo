/**
 * Created by Knove on 2017/11/9.
 */
import React from 'react';
import PendOrder from './OrderingComponents/PendOrder';
import SelectFood from './OrderingComponents/SelectFood';
import LackFood from './OrderingComponents/LackFood';
import OrderDetails from './OrderingComponents/OrderDetails';
class UserOrdering extends React.Component{
    render(){
        let ScreenHeight=document.body.clientHeight-115; //获取 全屏幕减去title的高度
        return(
            <div className="orderingMain" >
                <section className="mainLeft">
                    <PendOrder />
                </section>
                <section className="mainCenter" style={{height:ScreenHeight}}>
                    <LackFood style={{height:'30%'}} />
                    <SelectFood style={{height:'70%'}} />
                </section>
                <section className="mainRight">
                    <OrderDetails />
                </section>
            </div>
        )
    }
}
export default UserOrdering;
