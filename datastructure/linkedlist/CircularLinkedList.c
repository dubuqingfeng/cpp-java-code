struct node
{
	int data;
	struct node* next;
};

void push(struct node** head_ref, int new_data)
{
	struct node *new_node = (struct node *)malloc(sizeof(struct node));

	struct node *temp = *head_ref;

	new_node->data = new_data;
	new_node->next = *head_ref;

	if(*head_ref != NULL)
	{
		while(temp->next != *head_ref)
			temp = temp->next;
		temp->next = new_node;
	}else{
		new_node->next = new_node;
	}
	*head_ref = new_node;
}

void printList(struct node *head)
{
	struct node *temp = head;
	if(head != NULL)
	{
		do{
			printf("%d ", temp->data);
			temp = temp->next;
		}
		while(temp != head);
	}
}