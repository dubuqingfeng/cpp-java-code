struct node
{
	int data;
	struct node* next;
};

void push(struct node** head_ref, int new_data)
{
	struct node** new_node = (struct node*)malloc(sizeof(struct node));

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

bool search()
{
	
}

int main(int argc, char const *argv[])
{
	
	return 0;
}