/**
 * Created by Knove on 2017/11/13.
 *  描述：制造菜品的工厂
 */
import React from 'react';
import { Card  } from 'antd';
import { connect } from 'react-redux'; // 引入connect
import { addFoodDetails,pointNowDesk} from '../../action/action';


class FoodFactory extends React.Component{
    constructor(props) {
        super(props);
        this.state = {
        }
        this.addFoods=this.addFoods.bind(this);
        this.nowDeskNumber=this.nowDeskNumber.bind(this);
    }
    addFoods(index,nowDeskNum,nowFoodNum,foodName,foodPrice,foodNum){
        const { addFoodDetails } = this.props;
        console.log(nowDeskNum);
        if(nowDeskNum==null){
            console.log("请选择桌号");
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
        const foodList=[
            {id:1,name: "酸辣土豆丝", num: "32",price:"13"},
            {id:2,name: "麻婆豆腐", num: "12",price:"23"},
            {id:3,name: "水煮肉片", num: "12",price:"23"},
            {id:4,name: "水煮肉片", num: "12",price:"23"},
            {id:5,name: "水煮肉片", num: "12",price:"23"},
            {id:6,name: "水煮肉片", num: "12",price:"23"},
            {id:7,name: "水煮肉片", num: "12",price:"23"},
            {id:8,name: "水煮肉片", num: "12",price:"23"},
            {id:9,name: "水煮肉片", num: "12",price:"23"},
            {id:10,name: "水煮肉片", num: "12",price:"23"},
            {id:11,name: "水煮肉片", num: "12",price:"23"},



        ];
       let factory =foodList.map( (item,index) =>{
            if(index==5||index==8) return  <Card.Grid className="foodButton normalFood edFood">{item.name} {item.price}¥</Card.Grid>
            else if(index==2) return  <Card.Grid className="foodButton normalFood lackFood">{item.name} {item.price}¥</Card.Grid>
            else return  <Card.Grid className="foodButton normalFood"
                                    onClick={index=>this.addFoods(item.id,nowDeskNum,nowFoodNum,item.name,item.price,item.num)}>
                    {item.name} {item.price}¥
                </Card.Grid>
       })
        return(
            <Card noHovering bordered={false} className="factoryFood">
            {factory}
            </Card>
        )
    }
}
const mapStateToProps  = (state) => {
    let nowDeskNumber= state.httpData.deskNumber;
    if(nowDeskNumber==null)nowDeskNumber=0;
    return { nowDeskNumber: state.httpData.deskNumber,
        getDeskFoodArraynum:state.httpData.deskTable[nowDeskNumber].foodArray.length
    };
}
//connect 实现， mapStateToProps将state传入props，参数2 将 action 作为 props 绑定到 MyComp 上
FoodFactory = connect(mapStateToProps , {addFoodDetails,pointNowDesk})(FoodFactory);

export default FoodFactory;
