/*
 *	copyright (c) 2014.xx company
 *	All rights reserved
 *
 *	文件名称：
 *	文件标识：
 *	文件摘要：
 *
 *	当前版本：
 *	完成日期：
 *
 */

#include <iostream>
#include <cstdlib>
#include <fstream>
using namespace std;

class student
{
	public:
		initialise();
		static save();
		input();
		fstream f;

	private:
		int Number;
		char Classes[20],Name[20];
		float ElectronScore,CppScore,MediaScore,MathScore,PeScore,EnglishScore,PoliticsScore,AverageScore;

};


/*
class teacher
{
	private:
		long int number;
		string name;
		string sex;
		int year;
public:
		teacher(){
			cout<<"构造函数"<<endl;
 
		}
		teacher(teacher&tea);
		teacher(int tyear,string tsex,string name);
		virtual ~teacher(){
			cout<<"析构函数";
}
/**
Description: 传入值
Arguments:string tname,long int tnumber,string tsex,int tyear
Returns:
*//*
void input(string tname,long int tnumber,string tsex,int tyear)
{
name = tname;
year = tyear;
number = tnumber;
sex = tsex;
}
/**
Description: 改变年份
Arguments:
Returns:
*//*
void ChangeYear(int tyear)
{
year = tyear;
 
}
void ChangeName(string tname)
{
name = tname;
}
void ChangeNumber(long int tnumber)
{
number = tnumber;
}
void ChangeSex(string tsex)
{
sex = tsex;
}
void CoutData()
{
cout<<"姓名为："<<name<<endl<<"工号为："<<number<<endl<<"性别为："<<sex<<endl<<"出生年份为："<<year;
}
};
teacher::teacher(teacher&tea)
{
std::cout<<"拷贝构造函数调用"<<std::endl;
}
teacher::teacher(int tyear,string tsex,string name)
{
cout<<"带参构造函数"<<endl;
}*/
int main()
{
	int sNumber;
	char sClasses[20],sName[20];
	float sElectronScore,sCppScore,sMediaScore,sMathScore,sPeScore,sEnglishScore,sPoliticsScore,sAverageScore;

	//初始化文件操作
	fstream f("d.txt",ios::out);
	student stu[56];
	

	while(1) 
	{
		int i;

		cout<<"***********************************************************************"<<endl;
		cout<<endl;
		cout<<"---------------------<<欢迎您使用学生成绩管理系统>>--------------------"<<endl;
		cout<<endl;
		cout<<"***********************************************************************"<<endl;
		cout<<endl;
		cout<<"  *			【1】输入学生成绩                     *"<<endl;
		cout<<"  *			【2】显示统计数据                     *"<<endl;
		cout<<"  *			【3】查找学生成绩                     *"<<endl;
		cout<<"  *			【4】修改学生成绩                     *"<<endl;
		cout<<"  *			【5】删除学生成绩                     *"<<endl;
		cout<<"  *			【6】插入学生成绩                     *"<<endl;
		cout<<"  *			【7】按平均分降序排列                 *"<<endl;
		cout<<"  *			【8】显示全部学生成绩                 *"<<endl;
		cout<<"  *			【0】退出本系统                       *"<<endl;
		cout<<endl;
		cout<<"***********************************************************************"<<endl;
		cout<<"请输入要执行的内容的编号:";
		
		cin>>i;
			
		switch(i)
		{
			//输入学生成绩
			case 1:
				while(1)
				{
				system("cls");
				cout<<"-------------->> 请输入学生成绩 <<---------------"<<endl;
				cout<<"请输入班级：";
				cin>>sClasses;

				cout<<"请输入学号：";
				cin>>sNumber;

				cout<<"请输入姓名：";
				cin>>sName;

				cout<<"请输入电子技术成绩：";
				cin>>sElectronScore;

				cout<<"请输入c++程序设计技术成绩：";
				cin>>sCppScore;

				cout<<"请输入多媒体技术成绩：";
				cin>>sMediaScore;

				cout<<"请输入大学英语成绩：";
				cin>>sEnglishScore;

				cout<<"请输入高等数学成绩：";
				cin>>sMathScore;

				cout<<"请输入大学体育成绩：";
				cin>>sPeScore;

				cout<<"请输入马克思主义政治经济学成绩：";
				cin>>sPoliticsScore;

				cout<<"平均分为:";
				sAverageScore = (sElectronScore+sCppScore+sMediaScore+sMathScore+sPeScore+sEnglishScore+sPoliticsScore)/6.0;
				cout<<sAverageScore;

				//保存到文件
				f<<sNumber<<' '<<sClasses<<' '<<sName<<' '<<sElectronScore<<' '<<sCppScore<<' '<<sMediaScore<<' '<<sMathScore<<' '<<sPeScore<<' '<<sEnglishScore<<' '<<sPoliticsScore<<' '<<sAverageScore;

				cout<<"---->>提示：是否继续写入学生成绩？（y/n)";
				char r;
				cin>>r;

				if(r == 'y')
				{	
					//继续写入成绩

				}
				else
				{
					break;
				}
				}

			//显示统计数据
			case 2:
				system("cls");
				cout<<"电子技术平均分：";
				cout<<"电子技术平均分：";

				
				break;

			//查找学生成绩
			case 3:
				system("cls");
				cout<<"请输入要查找的学号：";
				
				break;

			//修改学生成绩
			case 4:
				system("cls");
				cout<<"请输入要修改成绩的学号：";
			
				break;

			//删除学生成绩
			case 5:
				system("cls");

				break;
			
			//插入学生成绩
			case 6:
				system("cls");
				cout<<"请输入新的出生年份：";
			
				break;
			
			//按平均分降序排列
			case 7:
			
				system("pause");
				exit(0);
				break;

			//显示全部学生成绩
			case 8:
				system("cls");
				if(!f)
				{
					cout<<"File open error!";
					
				}
				char c[80];

				while(f.get(c,80,'\0')!=NULL)cout<<c; 

				//cout<<c;
				f.close;
				system("pause");
				break;

			//退出本系统
			case 0:
				exit(0);

			default:
				cout<<"输入错误，请重新输入";
				system("pause");
				break;
			}
			system("cls");
		}

	return 0;
}