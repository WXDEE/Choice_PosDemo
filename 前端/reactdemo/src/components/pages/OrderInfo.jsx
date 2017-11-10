/**
 * Created by Knove on 2017/11/9.
 */
import React from 'react';
import { Button} from 'antd';
import { addNum } from '../../action/action';
import { connect } from 'react-redux'; // 引入connect


class OrderInfo extends React.Component{

    constructor(props) {
        super(props);
        this.state = {
        }
       this.add=this.add.bind(this);
    }
    add() {
        const { addNum } = this.props;
        addNum();
    }
    render(){
        const theNumber = this.props.dataA;
        return(
            <div>
                OrderInfo --
                <span>{theNumber}</span>
                <Button type="primary" onClick={this.add}>Add</Button>
                <div className="demo">
                    后台运维页面-订单明细
                </div>
            </div>
        )
    }

}
const mapStateToProps  = (state) => {
    return { dataA: state.httpData.theNumber};
}
//connect 实现， mapStateToProps将state传入props，参数2 将 action 作为 props 绑定到 MyComp 上
OrderInfo = connect(mapStateToProps , {addNum})(OrderInfo);
export default OrderInfo;


