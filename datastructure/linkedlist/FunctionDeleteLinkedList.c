/*
* write a function to delete a LinkedList
*
*/
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

void deleteList(struct node** head_ref)
{
	struct node* current = *head_ref;
	struct node* next;

	while(current != NULL)
	{
		next = current->next;
		free(current);
		current = next;
	}

	*head_ref = NULL;
}

int main(int argc, char const *argv[])
{
	struct node* head = NULL;

	push(&head, 2);
	push(&head, 8);
	push(&head, 10);

	printList(head);

	deleteList(&head);

	printList(head);

	printf("test");

	getchar();

	return 0;
}