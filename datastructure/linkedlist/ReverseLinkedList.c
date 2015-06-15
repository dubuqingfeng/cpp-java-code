#include <stdio.h>
#include <stdlib.h>

struct node
{
	int data;
	struct node* next;
};

void reverse(struct node** head_ref)
{
	struct node* prev = NULL;
	struct node* current = *head_ref;
	struct node* next;
	while(current != NULL)
	{
		next = current->next;
		current->next = prev;
		prev = current;
		current = next;
	}
	*head_ref = prev;
}

void push(struct node** head_ref, int new_data)
{
	struct node* new_node = (struct *node) malloc(sizeof(struct node));

	new_node->data = new_data;
	new_node->next = (*head_ref);

	(*head_ref) = new_node;
}

void printList(struct node *head)
{
	struct node *temp = head;
	while(temp != NULL)
	{
		printf("%d\n", temp->data);
		temp = temp->next;
	}
}

int main(int argc, char const *argv[])
{
	struct node* head = NULL;

	push(&head, 20);
	push(&head, 3);
	push(&head, 8);
	push(&head, 1);

	printList(head);
	reverse(&head);

	printf("Reversed Linked list");

	printList(head);
	return 0;
}