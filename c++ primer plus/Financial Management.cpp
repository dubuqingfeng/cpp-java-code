#include <iostream>
#include <cstdio>
using namespace std;
#define maxn 12
double arr[maxn];

int main()
{
   double sum = 0;
   double aver;
    //int x = 12;
    while(cin>>arr[0])
    { 
    	sum = arr[0];
		for(int i = 1;i< 12;i++)
		{
			cin>>arr[i];
			sum = sum +arr[i];
		}
		aver = sum/12.0;
		cout.setf(ios::fixed);
		cout.precision(2);
		cout<<"$"<<aver<<endl;
    }
    return 0;
}
