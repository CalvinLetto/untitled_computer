import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Calculator1 extends JFrame implements ActionListener {

	double num1, num2, num3;
	// 判断运算类型
	boolean add, mul, sub, div;
	// 判断输入的数字是否结束
	boolean end;
	// 北部
	JPanel p1 = new JPanel();
	// 文本框
	JTextField t = new JTextField("0");

	// 南部
	JPanel p2 = new JPanel();
	JButton b[] = new JButton[16];

	public void init() {
		b[0] = new JButton("1");
		b[1] = new JButton("2");
		b[2] = new JButton("3");
		b[3] = new JButton("+");
		b[4] = new JButton("4");
		b[5] = new JButton("5");
		b[6] = new JButton("6");
		b[7] = new JButton("-");
		b[8] = new JButton("7");
		b[9] = new JButton("8");
		b[10] = new JButton("9");
		b[11] = new JButton("*");
		b[12] = new JButton(".");
		b[13] = new JButton("0");
		b[14] = new JButton("=");
		b[15] = new JButton("/");
	}

	public Calculator1() {
		this.setTitle("计算器");
		this.setVisible(true);
		// 设置窗体不能改变
		this.setResizable(false);
		// 设置窗体关闭时进程关闭
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// 设置页面出现位置
		this.setBounds(200, 200, 320, 320);
		// p1添加在窗口上
		this.add(p1, "North");
		// 文本框设置在p1上
		p1.add(t);
		// 设置文本框宽度
		t.setColumns(27);
		// 设置文本内容位置
		t.setHorizontalAlignment(JTextField.RIGHT);
		// 设置文本框不可编辑
		t.setEditable(false);
		// 设置网格布局4*4
		GridLayout g1 = new GridLayout(4, 4);
		// 设置水平距离
		g1.setHgap(10);
		// 设置垂直距离
		g1.setVgap(10);
		p2.setLayout(g1);
		// 把p2添加到窗口中
		this.add(p2);
		// 初始化按钮
		init();
		for (int i = 0; i < 16; i++) {
			p2.add(b[i]);
		}

		// 西部
		// 创建一个标签
		JLabel jb1 = new JLabel();
		// 设置标签的面积
		jb1.setPreferredSize(new Dimension(10, 0));
		this.add(jb1, "West");

		// 东部
		// 创建一个标签
		JLabel jb2 = new JLabel();
		// 设置标签的面积
		jb2.setPreferredSize(new Dimension(10, 0));
		this.add(jb2, "East");
		
		//监听按钮
		for(int i=0;i<16;i++){
			b[i].addActionListener(this);
		}

	}
//处理数据
	public void num(int i) {
		String s = String.valueOf(i);
		//判断输入是否结束
		if(end){
			//文本框初始化
			t.setText("0");
			end = false;
		}
		//覆盖文本框
		if(t.getText().equals("0")){
			t.setText(s);
		}
		//拼接输入
		else{
			String str = t.getText()+s;
			t.setText(str);
		}
		
	}
//处理运算符
	public void sign(int i) {
		//加法
		if(i==1){
			add=true;
			mul=false;
			sub=false;
			div=false;
		}
		//减法
		if(i==2){
			add=false;
			mul=false;
			sub=true;
			div=false;
		}
		//乘法
		if(i==3){
			add=false;
			mul=true;
			sub=false;
			div=false;
		}
		//除法
		if(i==4){
			add=false;
			mul=false;
			sub=false;
			div=true;
		}
		num1 = Double.parseDouble(t.getText());
		end=true;
	}
//小数点
	public void point() {
		String s;
		//判断此时数字是否有小数点
		if(t.getText().indexOf('.')<0){
		s=t.getText()+".";
		t.setText(s);
		}
	}
//等于运算符
	public void eql() {
		num2 = Double.parseDouble(t.getText());
		if(add){
			num3=num1+num2;
		}
		if(sub){
			num3=num1-num2;
		}
		if(mul){
			num3=num1*num2;
		}
		if(div){
			num3=num1/num2;
		}
		String s = String.valueOf(num3);
		t.setText(s);
		end=true;
		
	}

	public static void main(String[] args) {
		Calculator1 c = new Calculator1();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==b[0]){
			num(1);
		}
		if(e.getSource()==b[1]){
			num(2);
		}
		if(e.getSource()==b[2]){
			num(3);
		}
		if(e.getSource()==b[3]){
			sign(1);
		}
		if(e.getSource()==b[4]){
			num(4);
		}
		if(e.getSource()==b[5]){
			num(5);
		}
		if(e.getSource()==b[6]){
			num(6);
		}
		if(e.getSource()==b[7]){
			sign(2);
		}
		if(e.getSource()==b[8]){
			num(7);
		}
		if(e.getSource()==b[9]){
			num(8);
		}
		if(e.getSource()==b[10]){
			num(9);
		}
		if(e.getSource()==b[11]){
			sign(3);
		}
		if(e.getSource()==b[12]){
			point();
		}
		if(e.getSource()==b[13]){
			num(0);
		}
		if(e.getSource()==b[14]){
			eql();
		}
		if(e.getSource()==b[15]){
			sign(4);
		}
		
	}

}
