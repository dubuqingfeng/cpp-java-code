#include <iostream>
using namespace std;
int main()
{
	int x,y,z;
	while(cin>>x)
	{
		for(y = 0;y < x;y++)
		cout<<y<<" "<<x-y<<endl;
	}
	return 0;
}
