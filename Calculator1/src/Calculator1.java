import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Calculator1 extends JFrame implements ActionListener {

	double num1, num2, num3;
	// �ж���������
	boolean add, mul, sub, div;
	// �ж�����������Ƿ����
	boolean end;
	// ����
	JPanel p1 = new JPanel();
	// �ı���
	JTextField t = new JTextField("0");

	// �ϲ�
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
		this.setTitle("������");
		this.setVisible(true);
		// ���ô��岻�ܸı�
		this.setResizable(false);
		// ���ô���ر�ʱ���̹ر�
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// ����ҳ�����λ��
		this.setBounds(200, 200, 320, 320);
		// p1����ڴ�����
		this.add(p1, "North");
		// �ı���������p1��
		p1.add(t);
		// �����ı�����
		t.setColumns(27);
		// �����ı�����λ��
		t.setHorizontalAlignment(JTextField.RIGHT);
		// �����ı��򲻿ɱ༭
		t.setEditable(false);
		// �������񲼾�4*4
		GridLayout g1 = new GridLayout(4, 4);
		// ����ˮƽ����
		g1.setHgap(10);
		// ���ô�ֱ����
		g1.setVgap(10);
		p2.setLayout(g1);
		// ��p2��ӵ�������
		this.add(p2);
		// ��ʼ����ť
		init();
		for (int i = 0; i < 16; i++) {
			p2.add(b[i]);
		}

		// ����
		// ����һ����ǩ
		JLabel jb1 = new JLabel();
		// ���ñ�ǩ�����
		jb1.setPreferredSize(new Dimension(10, 0));
		this.add(jb1, "West");

		// ����
		// ����һ����ǩ
		JLabel jb2 = new JLabel();
		// ���ñ�ǩ�����
		jb2.setPreferredSize(new Dimension(10, 0));
		this.add(jb2, "East");
		
		//������ť
		for(int i=0;i<16;i++){
			b[i].addActionListener(this);
		}

	}
//��������
	public void num(int i) {
		String s = String.valueOf(i);
		//�ж������Ƿ����
		if(end){
			//�ı����ʼ��
			t.setText("0");
			end = false;
		}
		//�����ı���
		if(t.getText().equals("0")){
			t.setText(s);
		}
		//ƴ������
		else{
			String str = t.getText()+s;
			t.setText(str);
		}
		
	}
//���������
	public void sign(int i) {
		//�ӷ�
		if(i==1){
			add=true;
			mul=false;
			sub=false;
			div=false;
		}
		//����
		if(i==2){
			add=false;
			mul=false;
			sub=true;
			div=false;
		}
		//�˷�
		if(i==3){
			add=false;
			mul=true;
			sub=false;
			div=false;
		}
		//����
		if(i==4){
			add=false;
			mul=false;
			sub=false;
			div=true;
		}
		num1 = Double.parseDouble(t.getText());
		end=true;
	}
//С����
	public void point() {
		String s;
		//�жϴ�ʱ�����Ƿ���С����
		if(t.getText().indexOf('.')<0){
		s=t.getText()+".";
		t.setText(s);
		}
	}
//���������
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
