#include <stdio.h>
#include <stdlib.h>

struct node
{
	int data;
	struct node* next;
};

void push(struct node** head_ref, int new_data)
{
	struct node* new_node = (struct node*) malloc(sizeof(struct node));

	new_node->data = new_data;
	new_node->next = (*head_ref);

	(*head_ref) = new_node;
}

void printList(struct node *node)
{
	while(node != NULL)
	{
		printf("%d\n", node->data);
		node = node->next;
	}


}

int getCount(struct node *node)
{
	int count = 0;
	while(node != NULL)
	{
		count++;
		node = node->next;
	}
	return count;
}

int main(int argc, char const *argv[])
{
	struct node* head = NULL;
	push(&head, 7);
	push(&head, 8);
	push(&head, 5);

	printList(head);

	printf("%d\n", getCount(head));

	getchar();
	return 0;
}