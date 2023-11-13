package data.tu;

import data.map.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*  基于邻接矩阵实现的无向图类 */
public class GrapAdjMat {
    //定点
    List<Integer> vertices;
    //边
    List<List<Integer>> adjMat;

    //初始化
    public GrapAdjMat(int[] vertices,int[][] adjMat){
        this.vertices = new ArrayList<>();
        this.adjMat = new ArrayList<>();
        //添加定点
        for(int var:vertices){
            addVertex(var);
        }
        //添加边
        for(int[] ad:adjMat){
            addEdge(ad[0],ad[1]);
        }
    }
    //获取顶点数量
    public int size(){
        return vertices.size();
    }
    //添加定点
    public void addVertex(int val){
        int n = size();
        vertices.add(val);
        //在邻接矩阵添加一行
        List<Integer> newRow = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            newRow.add(0);
        }
        adjMat.add(newRow);
        //在邻接矩阵添加一列
        for(List<Integer> adj:adjMat){
            adj.add(0);
        }
    }
    //删除定点
    public void removeVertex(int index){
        if(index == size()){
            throw new IndexOutOfBoundsException();
        }
        int n = size();
        vertices.remove(index);
        //删除行
        adjMat.remove(index);
        //删除列
        for (List<Integer> ad:adjMat) {
            ad.remove(index);
        }
    }
    //添加边
    public void addEdge(int i,int j){
        if(i < 0 || j < 0 || i >= size() || j >= size() || i == j){
            throw new IndexOutOfBoundsException();
        }
        adjMat.get(i).add(j,1);
        adjMat.get(j).add(i,1);
    }
    //删除边
    public void removeEdge(int i,int j){
        if(i < 0 || j < 0 || i >= size() || j >= size() || i == j){
            throw new IndexOutOfBoundsException();
        }
        adjMat.get(i).add(j,0);
        adjMat.get(j).add(i,0);
    }
    //打印邻接矩阵
    public void print(){
        System.out.print(" 顶点列表 = ");
        System.out.println(vertices);
        System.out.println(" 邻接矩阵 =");

    }
}
