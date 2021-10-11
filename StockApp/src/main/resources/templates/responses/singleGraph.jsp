<!DOCTYPE HTML>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<%@page import="java.util.ArrayList" %>
<%@page import ="com.stocks.domain.Price"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<% String name = (String)request.getAttribute("companyName"); %>
<% ArrayList<Map<String, Integer>> dates = (ArrayList<Map<String, Integer>>) request.getAttribute("dateList"); %>
<% List<Price> data = (List<Price>) request.getAttribute("priceList"); %>
<head>
    <script type="text/javascript" src="https://canvasjs.com/assets/script/jquery-1.11.1.min.js"></script>
    <script type="text/javascript" src="https://canvasjs.com/assets/script/canvasjs.stock.min.js"></script>
    <script type="text/javascript">
        var dataPoints1 = [], dataPoints2 = [], dataPoints3 = []
        function createDataPoints() {
            <% for (int i = 0; i < data.size(); i++) { %>
                <% Price price = (Price) data.get(i) %>
                var date = <%=price.getDateSymbol().getDate() %>
                var open = <%=price.getOpen() %>
                var close = <%=price.getClose() %>
                var open = <%=price.getOpen() %>
                var high = <%=price.getHigh() %>
                var open = <%=price.getOpen() %>
            <%}%>
        }
        createDataPoints()
        window.onload = function () {
            var dataPoints1 = [], dataPoints2 = [], dataPoints3 = [];
            var stockChart = new CanvasJS.StockChart("chartContainer",{
                exportEnabled: true,
                theme: "light2",
                title:{
                    text:"<%=name%>"
                },
                charts: [{
                    toolTip: {
                        shared: true
                    },
                    axisX: {
                        lineThickness: 5,
                        tickLength: 0,
                        labelFormatter: function(e) {
                            return "";
                        },
                        crosshair: {
                            enabled: true,
                            snapToDataPoint: true,
                            labelFormatter: function(e) {
                                return ""
                            }
                        }
                    },
                    axisY2: {
                        title: "Litecoin Price",
                        prefix: "€"
                    },
                    legend: {
                        verticalAlign: "top",
                        horizontalAlign: "left"
                    },
                    data: [{
                        name: "Price (in EUR)",
                        yValueFormatString: "€#,###.##",
                        axisYType: "secondary",
                        type: "candlestick",
                        risingColor: "green",
                        fallingColor: "red",
                        dataPoints : dataPoints1
                    }]
                },{
                    height: 100,
                    toolTip: {
                        shared: true
                    },
                    axisX: {
                        crosshair: {
                            enabled: true,
                            snapToDataPoint: true
                        }
                    },
                    axisY2: {
                        prefix: "€",
                        title: "LTC/EUR"
                    },
                    legend: {
                        horizontalAlign: "left"
                    },
                    data: [{
                        yValueFormatString: "€#,###.##",
                        axisYType: "secondary",
                        name: "LTC/EUR",
                        dataPoints : dataPoints2
                    }]
                }],
                navigator: {
                    data: [{
                        color: "grey",
                        dataPoints: dataPoints3
                    }],
                    slider: {
                        minimum: new Date(<%=dates.get(0).get("year")%>, <%=dates.get(0).get("month")%>, <%=dates.get(0).get("day")%>),
                        maximum: new Date(<%=dates.get(1).get("year")%>, <%=dates.get(1).get("month")%>, <%=dates.get(1).get("day")%>)
        }
        }
        });
            $.getJSON("https://canvasjs.com/data/docs/ltceur2018.json", function(data) {
                for(var i = 0; i < data.length; i++){
                    dataPoints1.push({x: new Date(data[i].date), y: [Number(data[i].open), Number(data[i].high), Number(data[i].low), Number(data[i].close)], color: data[i].open < data[i].close ? "green" : "red"});;
                    dataPoints2.push({x: new Date(data[i].date), y: Number(data[i].volume_eur), color: data[i].open < data[i].close ? "green" : "red"});
                    dataPoints3.push({x: new Date(data[i].date), y: Number(data[i].close)});
                }
                stockChart.render();
            });
        }
    </script>
</head>
<body>
<div id="chartContainer" style="height: 400px; max-width: 920px; margin: 0px auto;"></div>
</body>
</html>