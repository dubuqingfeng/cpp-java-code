#include <iostream>
#include <cstdio>
#include <algorithm>
using namespace std;
#define maxn 1000+10
double arr[maxn];

int main()
{
   
    int x;
    cin>>x;
    while(x--)
    { 
		int n;
		cin>>n;
		for(int i = 0;i<n;i++)
		{
		cin>>arr[i];
		}
		sort(arr,arr+n);
		cout.setf(ios::fixed);
		cout.precision(2);
		cout<<arr[n-1]<<endl;
    }
    return 0;
}
