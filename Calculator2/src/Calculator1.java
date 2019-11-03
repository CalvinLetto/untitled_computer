import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

import javax.swing.*;

public class Calculator1 extends JFrame {
	// ����
	JPanel p1 = new JPanel();
	// �ı���
	JTextField t = new JTextField("");

	// �ϲ�
	JPanel p2 = new JPanel();
	JButton b[] = new JButton[20];
	
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
		b[16] = new JButton("(");
		b[17] = new JButton(")");
		b[18] = new JButton("Back");
		b[19] = new JButton("C");	
	}

	public Calculator1() {
		this.setTitle("������");
		// ���ô��岻�ܸı�
		this.setResizable(false);
		// ����ҳ�����λ��
		this.setBounds(40, 45, 400, 350);
		// p1����ڴ�����
		this.add(p1, "North");
		// �ı���������p1��
		p1.add(t);
		// �����ı�����
		t.setColumns(27);
		// �����ı�����λ��
		t.setHorizontalAlignment(JTextField.RIGHT);
		// �����ı��򲻿ɱ༭
		//t.setEditable(false);
		// �������񲼾�5*4
		GridLayout g1 = new GridLayout(5, 4);
		// ����ˮƽ����
		g1.setHgap(10);
		// ���ô�ֱ����
		g1.setVgap(10);
		p2.setLayout(g1);
		// ��p2��ӵ�������
		this.add(p2);
		// ��ʼ����ť
		init();
		for (int i = 0; i < 20; i++) {
			p2.add(b[i]);
			b[i].addActionListener(new Event1(this));
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
		
		
		this.setVisible(true);
		// ���ô���ر�ʱ���̹ر�
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

class Event1 implements ActionListener {
		Calculator1 evd;
		Event1(Calculator1 evd) {
			this.evd=evd;
		}
		public void actionPerformed(ActionEvent e) {
			String str=evd.t.getText();
			for(int i=0;i<20;i++) {
					if(e.getSource()==evd.b[i]) {
						//=��
						if(i==14) {
							Calculate c=new Calculate(str);
							c.transform();
							if(c.value().equals("error"))
								evd.t.setText("");
							else
							    evd.t.setText(c.value());
						}
						//ɾ����
						else if(i==18) {
							if(evd.t.getText().length()>0)
						         evd.t.setText(str.substring(0, str.length()-1));
							}
						//C  ���
						else if(i==19) 
							evd.t.setText("");
						else
					        evd.t.setText(str+evd.b[i].getText());
				    }
			}
		}	
	}	


class WarningGUI extends JFrame{
	JLabel label;
	WarningGUI (String text){
		this.setTitle("Warning");
		this.setSize(200,80);
		label=new JLabel(text);
		//���ñ�ǩ����������
		label.setFont(new Font(Font.DIALOG,Font.PLAIN,25));
		//���ô��ڵĲ��ֹ���
		this.setLayout(new FlowLayout());
		//���ô��ڳ�����������
		this.setLocationRelativeTo(null);
		//���ɸı䴰�ڴ�С
		this.setResizable(false);
		this.add(label);
		//��ʾ����
		this.setVisible(true);
		//�ر�ʱ�˳�����
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}

class Stack {
	char []s=new char [20];
	int length;
	Stack() {
		this.length=0;
	}
	//��Ԫ������ջ��
	void push(char c) {
		s[length]=c;
		length++;
	}
	//ȡ��ջ��Ԫ�أ���ɾ��
	char pop() {
		this.length--;
		return s[this.length];
	}
	//��ȡջ��Ԫ��
	char getTop() {
		return s[this.length-1];
	}
}

class StackPro {
	float []s=new float [40];
	int length;
	StackPro() {
		this.length=0;
	}
	void push(float c) {
		s[length]=c;
		length++;
	}
	float pop() {
		this.length--;
		return s[this.length];
	}
	float getTop() {
		return s[this.length-1];
	}
}


@SuppressWarnings("serial")
class DivisorException extends Exception {
	DivisorException() {
		super("��������Ϊ0");
	}
}


class Calculate {
	String str;
	Calculate(String str) {
		this.str=str;
	}
	//�ж����ȼ�
	int prior(char x) {
		if(x=='*'||x=='/') return 4;
		else if(x=='+'||x=='-') return 3;
		else if(x=='(') return 2;
		else if(x=='#') return 1;
		else return 0;
	}
	void transform() {
		String s="";
		char c,x;
		//����ջ����
		Stack stack=new Stack();
		//���һ��#��ջ��
		stack.push('#');
		this.str+="#";
		for(int i=0;i<this.str.length();i++) {
			//ȡ���ַ����е��ַ�
			x=str.charAt(i);
			//�ж��Ƿ�������
			if(x>='0'&&x<='9'||x=='.') {
				s+=x;
				//���ַ���һ����Ϊ����ʱ 
				if((str.charAt(i+1)<'0'||str.charAt(i+1)>'9')&&str.charAt(i+1)!='.')
					s+=" ";
			}
			else switch(x) {
			case '(':stack.push(x);break;
			case ')':c=stack.pop();
			         while(c!='(') {
			        	 s+=c;
			        	 c=stack.pop();
			         }
			         break;
			default :c=stack.getTop();
			         while(prior(c)>=prior(x)) {
			        	 s+=c;
			        	 c=stack.pop();
			        	 if(stack.length==0) break;
			        	 c=stack.getTop();
			         }
			         if(x!='#') stack.push(x);
			}
		}
		this.str=s.substring(0, s.length()-1);
	}
	String value() {
		float x1,x2;
		StackPro stackPro=new StackPro();
		String s="";
		for(int i=0;i<this.str.length();i++) {
			char c=str.charAt(i);
			switch (c) {
			case 'a' :x1=stackPro.pop(); stackPro.push((float)(Math.atan(x1)));break;
			case '+':x2=stackPro.pop();x1=stackPro.pop();stackPro.push(x1+x2);break;
			case '-':x2=stackPro.pop();x1=stackPro.pop();stackPro.push(x1-x2);break;
			case '*':x2=stackPro.pop();x1=stackPro.pop();stackPro.push(x1*x2);break;
			case '/':x2=stackPro.pop();x1=stackPro.pop();
			try {
			         if(x2==0) 
			        	 throw new DivisorException();
			         stackPro.push(x1/x2);break;
			}
			catch (DivisorException e) {
				new WarningGUI(e.getMessage());
				return"error";
			}
			default:
			    while(str.charAt(i)!=' ') {
				    s+=str.charAt(i);
				    i++;
			    }
			    stackPro.push(Float.parseFloat(s));
			    s="";
			}
		}
		float y=stackPro.pop();
		BigDecimal b2 = new BigDecimal(y);
		y = b2.setScale(6, BigDecimal.ROUND_HALF_UP).floatValue();
		if((int)y==y)
			return Integer.toString((int)y);
		else
			return Float.toString(y);
	}
}


	public static void main(String[] args) {
		Calculator1 c = new Calculator1();
	}

	
}
