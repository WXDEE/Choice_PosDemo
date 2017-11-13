/**
 * Created by Knove on 2017/11/13.
 *  描述：制造菜品的工厂
 */
import React from 'react';
import { Card  } from 'antd';

class FoodFactory extends React.Component{

    render(){
        const foodList=[
            {name: "酸辣土豆丝", num: "32",price:"13"},
            {name: "麻婆豆腐", num: "12",price:"23"},
            {name: "水煮肉片",  num: "17",price:"33"},
            {name: "水煮肉片",  num: "17",price:"33"},
            {name: "水煮肉片",  num: "17",price:"34"},
            {name: "水煮肉片",  num: "17",price:"23"},
            {name: "水煮肉片",  num: "17",price:"33"},
            {name: "水煮肉片",  num: "17",price:"33"},
            {name: "水煮肉片",  num: "17",price:"53"},
            {name: "水煮肉片",  num: "17",price:"33"},
            {name: "水煮肉片",  num: "17",price:"33"},
            {name: "水煮肉片",  num: "17",price:"34"},

        ];
       let factory =foodList.map(function (item,index) {
            if(index==5||index==8) return  <Card.Grid className="foodButton normalFood edFood">{item.name} {item.price}¥</Card.Grid>
            else if(index==2) return  <Card.Grid className="foodButton normalFood lackFood">{item.name} {item.price}¥</Card.Grid>
            else return  <Card.Grid className="foodButton normalFood">{item.name} {item.price}¥</Card.Grid>
       })
        return(
            <Card noHovering bordered={false} className="factoryFood">
            {factory}
            </Card>
        )
    }
}
export default FoodFactory;
