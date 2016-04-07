#include <stdio.h>
#include <stdlib.h>
int main(void)
{
	int a[11], i, j, t;
	for(i = 0; i < 10; i++)
	{
		a[i] = 0;
	}
	for(i = 1; i < 7; i++)
	{
		scanf("%d", &t);
		a[t] ++;
	}
	for(i = 0; i < 10; i++)
	{
		for(j = 1; j <= a[i]; j++)
		{
			printf("%d ", i);
		}
	}
	system("pause");
	return 0;
}
