package cn.edu.hit.lib.model;


public class Book {
	public long id;
	public String title;//����
	public String author;//����
	public String publish;//��������
	public String year;//�������
	public String page;//ҳ��
	public String price;
	public String ISBN;//ISBN/ISSN
	public String subject;//����
	public String callNo;//��ȡ��
	public String relInfo;//�����Ϣ
	public String sort;//����
	public String frontIntro;//���
	
	public String toString(){
		if(title != null)
			return title;
		else
			return "null";
	}
}
