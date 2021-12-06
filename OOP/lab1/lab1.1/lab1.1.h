#pragma once

#include <iostream>
#include <vector>

#define order 5
#include "Header.h"


template< typename T>
class MatrixElement
{
public:
	int i, j;
	T data;
	MatrixElement<T>* next = nullptr;
	MatrixElement<T>* prev = nullptr;
};

template< typename T>
class MatrixElement_List
{
public:
	MatrixElement<T>* head = nullptr;
	MatrixElement<T>* end = nullptr;

	void add(int i, int j, T data)
	{
		MatrixElement<T>* e = new MatrixElement<T>;
		e->i = i;
		e->j = j;
		e->data = data;
		if (!head)
		{
			head = end = e;
		}
		else
		{
			e->next = head;
			head->prev = e;
			head = e;
		}
	}

	MatrixElement<T>* search_by_index(int i, int j) // ïîèñê ýëåìåíòà ïî èíäåêñó, ðåàëèçàöèÿ â âèäå ìåòîäà
	{
		MatrixElement<T>* e = new MatrixElement<T>;
		e = end;
		if (i < order && j < order)
			for (int k = 0; k < order; k++)
				for (int p = 0; p < order; p++)
				{
					if (!e || e->i >= i && e->j > j)
					{
						e = new MatrixElement<T>;
						e->i = i;
						e->j = j;
						e->data = 0;
						return e;
					}
					else if (e->i == i && e->j == j)
						return e;

					//std::cout << "e->i = " << e->i << '	' << "e->j = " << e->j << std::endl;
					e = e->prev;
				}
		std::cout << "Èíäåêñ áîëüøå ðàçìåðà ìàòðèöû!";
		e = new MatrixElement<T>;
		return e;
	}
};

template< typename T>
MatrixElement<T>* search_by_index(MatrixElement_List<T> horizontal[order], int i, int j) // ïîèñê ýëåìåíòà ïî èíäåêñó, ðåàëèçàöèÿ â âèäå ôóêíöèè
{
	MatrixElement<T>* e = new MatrixElement<T>;
	if (i < order && j < order)
	{
		e = horizontal[i].end;
		for (int k = 0; k < order; k++)
			for (int p = 0; p < order; p++)
			{
				if (!e || e->i >= i && e->j > j)
				{
					e = new MatrixElement<T>;
					e->i = i;
					e->j = j;
					e->data = 0;
					return e;
				}
				else if (e->i == i && e->j == j)
					return e;
				//std::cout << "e->i = " << e->i << '	' << "e->j = " << e->j << std::endl;
				e = e->prev;
			}
	}
	out_RED("Èíäåêñ áîëüøå ðàçìåðà ìàòðèöû!");
	e = new MatrixElement<T>;
	return e;
}

template< typename T>
MatrixElement<T>* search_first_by_value(MatrixElement_List<T> horizontal[order], double data) //ïîèñê ïåðâîãî ýëåìåíòà ïî çíà÷åíèþ
{
	MatrixElement<T>* e = new MatrixElement<T>;
	if (data) // åñëè ýòî çíà÷åíèå íå 0
	{
		for (int k = 0; k < order; k++)
		{
			e = horizontal[k].end;
			for (int p = 0; p < order; p++)
			{
				if (e && e->data == data)
					return e;
				else if (!e)
					break;
				//	std::cout << "e->i = " << e->i << '	' << "e->j = " << e->j << std::endl;
				e = e->prev;
			}
		}
	}
	else // åñëè çíà÷åíèå 0
	{
		for (int k = 0; k < order; k++)
		{
			e = horizontal[k].end;
			for (int p = 0; p < order; p++)
			{
				if (e->prev && e->j != e->prev->j - 1)
				{
					e->j = e->j + 1;
					e->data = 0;
					return e;
				}
				//std::cout << "e->i = " << e->i << '	' << "e->j = " << e->j << std::endl;
				//std::cout << "i = " << i << '	' << "j = " << j << std::endl;
				e = e->prev;
			}
		}

	}
	// åñëè ýëåìåíòà ñ òàêèì çíà÷åíèåì íåò â ìàòðèöå: 
	out_RED("Çíà÷åíèå íå íàéäåíî!");
	e = new MatrixElement<T>;
	return e;
}

template< typename T>
void search_all_by_value(MatrixElement_List<T> horizontal[order], std::vector< MatrixElement<T>*>& myVector, T data) //ïîèñê âñåõ ýëåìåíòîâ ïî çíà÷åíèþ
{
	MatrixElement<T>* e = new MatrixElement<T>;
	if (data)
	{
		for (int k = 0; k < order; k++)
		{
			e = horizontal[k].end;
			for (int p = 0; p < order && e; p++)
			{
				if (e && e->data == data)
					myVector.push_back(e);
				e = e->prev;
			}
		}
	}
	else
	{
		for (int k = 0; k < order; k++)
		{
			e = horizontal[k].end;
			for (int p = 0; p < order && e; p++)
			{
				if (e->prev && e->j != e->prev->j - 1)
				{
					int j_copy = e->j + 1;
					while (j_copy != e->prev->j)
					{
						MatrixElement<T>* element = new MatrixElement<T>;
						element->i = k;
						element->j = j_copy;
						element->data = 0;
						element->next = e;
						element->prev = e->prev;

						myVector.push_back(element);
						//std::cout << "j_copy = " << j_copy << std :: endl;
						//std::cout << "element->j = " << myVector[myVector.size()-1]->j << std :: endl;

						j_copy++;
					}
				}
				//std::cout << "e->i = " << e->i << '	' << "e->j = " << e->j << std::endl;
				//std::cout << "i = " << i << '	' << "j = " << j << std::endl;
				e = e->prev;
			}
		}
	}
}

template< typename T>
void print_matrix(MatrixElement_List<T> horizontal[order])
{//âûâîä â âèäå ìàòðèöû
	for (int i = 0; i < order; i++)
	{
		MatrixElement<T>* e = horizontal[i].end;

		int j_copy = -1;
		if (e == nullptr)
			for (int j = 0; j < order; j++)
				std::cout << '0' << ' ';
		while (e != nullptr)
		{
			if (j_copy == -1 && e->j != 0)
				for (int j = 0; j < e->j; j++)
					std::cout << '0' << ' ';
			else
				for (int j = j_copy + 1; j < e->j; j++)
					std::cout << '0' << ' ';
			std::cout << e->data << ' ';
			j_copy = e->j;

			if (e->prev == nullptr)
				for (int j = j_copy + 1; j < order; j++) std::cout << '0' << ' ';

			e = e->prev;
		}
		std::cout << std::endl;
	}
}

template< typename T>
void matrix_sum(MatrixElement_List<T> horizontal_A[order], MatrixElement_List<T> horizontal_B[order], MatrixElement_List<T>(&h_c)[order])
{//ñóììà ìàòðèö
	bool b = 0;
	for (int i = 0; i < order; i++) //ïðîâåðÿåì, íå ïóñòà ëè ìàòðèöà Â
		if (horizontal_B[i].end)
		{
			//std::cout << "b = " << b << std::endl;
			b = 1;
		}

	if (b)
	{
		for (int i = 0; i < order; i++)
		{
			MatrixElement<T>* h_a = horizontal_A[i].end;
			MatrixElement<T>* h_b = horizontal_B[i].end;
			if (h_a == nullptr && h_b != nullptr)
				while (h_b != nullptr)
				{
					h_c[i].add(h_b->i, h_b->j, h_b->data);
					h_b = h_b->prev;
				}
			while (h_a != nullptr)
			{
				if (h_b == nullptr)
					h_b = horizontal_B[i].head;
				while (h_b != nullptr)
				{
					if ((h_a->j == h_b->j) && ((h_a->data + h_b->data) != 0))
					{
						h_c[i].add(h_a->i, h_a->j, h_a->data + h_b->data);
						h_b = h_b->prev;
						break;
					}
					else if (h_a->j > h_b->j && h_b->prev == nullptr)
					{
						h_c[i].add(h_a->i, h_a->j, h_a->data);
						break;
					}
					else if (h_a->j > h_b->j)
						h_c[i].add(h_b->i, h_b->j, h_b->data);
					else if (h_a->j < h_b->j)
					{
						h_c[i].add(h_a->i, h_a->j, h_a->data);
						break;
					}
					h_b = h_b->prev;
				}
				h_a = h_a->prev;
			}
		}
	}
	else
	{
		for (int i = 0; i < order; i++)
			h_c[i] = horizontal_A[i];
	}
	//make_vertical_bundle(h_c, v_c);
}

template< typename T>
void make_vertical_bundle(MatrixElement_List<T> h_c[order], MatrixElement_List<T>(&v_c)[order])
{//ïîëó÷àåì âåðòèêàëüíóþ ñâÿçêó, èìåÿ ãîðèçîíòàëüíóþ
	for (int i = 0; i < order; i++)
	{
		MatrixElement<T>* e = h_c[i].end;
		while (e != nullptr)
		{
			v_c[e->j].add(e->i, e->j, e->data);
			e = e->prev;
		}
	}
}

template< typename T>
void matrix_multiplication(MatrixElement_List<T> horizontal_A[order], MatrixElement_List<T> horizontal_B[order], MatrixElement_List<T>(&h_c)[order])
{//óìíîæåíèå ìàòðèö
	MatrixElement_List<T> vertical_B[order];
	make_vertical_bundle(horizontal_B, vertical_B);

	for (int i = 0; i < order; i++)
	{
		for (int j = 0; j < order; j++)
		{
			int count = 0;
			MatrixElement<T>* h_a = horizontal_A[i].end;


			while (h_a != nullptr)
			{
				MatrixElement<T>* v_b = vertical_B[j].end;
				while (v_b != nullptr)
				{
					if (h_a->j == v_b->i)
						count += h_a->data * v_b->data;
					v_b = v_b->prev;
				}
				h_a = h_a->prev;
			}
			if (count != 0)
				h_c[i].add(i, j, count);
		}
	}
	//make_vertical_bundle(h_c, v_c);
}

template< typename T>
void matrix_transpose(MatrixElement_List<T> horizontal_A[order], MatrixElement_List<T>(&h_c)[order])
{
	MatrixElement_List<T> vertical_A[order];
	make_vertical_bundle(horizontal_A, vertical_A);


	for (int i = 0; i < order; i++)
	{
		MatrixElement<T>* e = vertical_A[i].end;
		while (e)
		{
			h_c[i].add(e->j, e->i, e->data);
			e = e->prev;
		}
	}
}

template< typename T>
MatrixElement_List<T> matrix_multiplication_by_vector(MatrixElement_List<T> horizontal_A[order], MatrixElement_List<T> vector)
{
	MatrixElement_List<T> vector_result;

	for (int j = 0; j < order; j++)
	{
		int count = 0;
		MatrixElement<T>* h_a = horizontal_A[j].end;


		while (h_a != nullptr)
		{
			MatrixElement<T>* vec = vector.end;
			while (vec)
			{
				if (h_a->j == vec->j)
					count += h_a->data * vec->data;
				vec = vec->prev;
			}
			h_a = h_a->prev;
		}
		if (count != 0)
			vector_result.add(0, j, count);
	}
	return vector_result;
}

void CREATING_AND_FILLING_THE_FILE_WITH_MATRIX_ELEMENTS() // ÑÎÇÄÀÍÈÅ È ÇÀÏÎËÍÅÍÈÅ ÔÀÉËÀ ÝËÅÌÅÍÒÀÌÈ ÌÀÒÐÈÖÛ
{
	FILE* file;
	if (fopen_s(&file, "matrix_A", "wb"))
		perror("Error");
	{
		for (int i = 0; i < pow(order, 2); i++)
		{
			int a = rand() % 2;
			fwrite(&a, sizeof(int), 1, file);
		}
	}
	fclose(file);

	if (fopen_s(&file, "matrix_B", "wb"))
		perror("Error");
	else
	{
		for (int i = 0; i < pow(order, 2); i++)
		{
			int a = rand() % 3;
			fwrite(&a, sizeof(int), 1, file);
		}
	}
	fclose(file);
}


template< typename T>
void matrix_creation(MatrixElement_List<T>(&horizontal)[order], const char m[10])
{//çàïîëíåíèå ìàòðèöû èç ôàéëà
	char name[20] = "matrix_";
	strcat_s(name, m);
	FILE* file;
	int A;

	if (fopen_s(&file, name, "rb"))
		perror("Error");
	else
	{
		for (int i = 0; i < order; i++)
			for (int j = 0; j < order; j++)
			{
				fread(&A, sizeof(int), 1, file);
				if (A != 0)
				{
					horizontal[i].add(i, j, A);
				}
			}
		std::cout << name << std::endl;
		print_matrix(horizontal);
		std::cout << std::endl;
	}
	fclose(file);
}

template< typename T>
void search_by_index_method_main(MatrixElement_List<T>(&horizontal_A)[order]) //ïîèñê ýëåìåíòà ïî èíäåêñó, âûçûâàåò ìåòîä êëàññà MatrixElement_List
{
	int i, j;
	out_BLUE_RED("Ïîèñê ýëåìåíòà ïî èíäåêñó, ðåàëèçàöèÿ â âèäå ìåòîäà");
	std::cout << "i = ";
	std::cin >> i;
	std::cout << "j = ";
	std::cin >> j;
	MatrixElement<T>* element = horizontal_A[i].search_by_index(i, j);
	std::cout << std::endl << "element->data = " << element->data;
}

template< typename T>
void search_by_index_function_main(MatrixElement_List<T>(&horizontal_A)[order]) //ïîèñê ýëåìåíòà ïî èíäåêñó, âûçûâàåò ôóíêöèþ search_by_index
{
	int i, j;
	out_BLUE_RED("Ïîèñê ýëåìåíòà ïî èíäåêñó, ðåàëèçàöèÿ â âèäå ôóíêöèè");
	std::cout << "i = ";
	std::cin >> i;
	std::cout << "j = ";
	std::cin >> j;
	MatrixElement<T>* element = search_by_index(horizontal_A, i, j);
	std::cout << std::endl << "data = " << element->data << std::endl;
}

template< typename T>
void search_first_by_value_main(MatrixElement_List<T>(&horizontal_A)[order]) 	//ïîèñê ïåðâîãî ýëåìåíòà ïî çíà÷åíèþ
{
	out_BLUE_RED("Ïîèñê ïåðâîãî ýëåìåíòà ïî çíà÷åíèþ");
	std::cout << "data = ";
	double data;
	std::cin >> data;
	MatrixElement<T>* element = search_first_by_value(horizontal_A, data);
	std::cout << "i = " << element->i << std::endl << "j = " << element->j << std::endl;
}

template< typename T>
void search_all_by_value_main(MatrixElement_List<T>(&horizontal_A)[order]) 	//ïîèñê âñåõ ýëåìåíòîâ ïî çíà÷åíèþ
{
	out_BLUE_RED("Ïîèñê âñåõ ýëåìåíòîâ ïî çíà÷åíèþ");
	std::cout << "data = ";
	double data;
	std::cin >> data;
	std::vector<MatrixElement<T>*> myVector;
	search_all_by_value(horizontal_A, myVector, data);
	for (MatrixElement<T>* element : myVector)
		std::cout << "element->i = " << element->i << '	' << "element->j = " << element->j << '	' << "element->data = " << element->data << std::endl;
	if (myVector.empty())
		out_RED("Òàêèõ ýëåìåíòîâ íåò!");
}

template< typename T>
void matrix_sum_main(MatrixElement_List<T>(&horizontal_A)[order], MatrixElement_List<T>(&horizontal_B)[order]) 	//ñóììà ìàòðèö
{
	MatrixElement_List<T> horizontal_SUM_A_B[order];
	matrix_sum(horizontal_A, horizontal_B, horizontal_SUM_A_B);
	std::cout << "horizontal_SUM_A_B" << std::endl;
	print_matrix(horizontal_SUM_A_B);
}

template< typename T>
void matrix_multiplication_main(MatrixElement_List<T>(&horizontal_A)[order], MatrixElement_List<T>(&horizontal_B)[order]) 	//óìíîæåíèå ìàòðèö
{
	MatrixElement_List<T> horizontal_SUM_A_B[order];
	matrix_multiplication(horizontal_A, horizontal_B, horizontal_SUM_A_B);
	std::cout << "horizontal_multiplication_A_B" << std::endl;
	print_matrix(horizontal_SUM_A_B);
}

template< typename T>
void matrix_transpose_main(MatrixElement_List<T>(&horizontal_A)[order]) //òðàíñïîíèðîâàíèå ìàòðèöû À
{
	MatrixElement_List<T> horizontal_transpose_A[order];
	matrix_transpose(horizontal_A, horizontal_transpose_A);
	std::cout << "horizontal_transpose_A" << std::endl;
	print_matrix(horizontal_transpose_A);
}

template< typename T>
void matrix_multiplication_by_vector_main(MatrixElement_List<T>(&horizontal_A)[order]) //óìíîæåíèå ìàòðèöû À íà âåêòîð
{
	MatrixElement_List<T> vector;
	for (int j = 0; j < order; j++)
	{
		int a = rand() % 3;
		if (a)
			vector.add(0, j, a);
	}

	std::cout << "vector" << std::endl;
	MatrixElement<T>* e = vector.end;
	for (int j = 0; j < order && e; j++)
	{
		std::cout << e->data << ' ';
		if (e->j != e->prev->j - 1)
		{
			//std::cout << std :: endl <<  'j = ' << j << std :: endl;
			for (; j < e->prev->j; j++)
				std::cout << '0' << ' ';
		}
		e = e->prev;
	}
	std::cout << std::endl << std::endl;

	MatrixElement_List<T> vector_result = matrix_multiplication_by_vector(horizontal_A, vector);

	std::cout << "vector_result" << std::endl;
	e = vector_result.end;
	for (int j = 0; j < order && e; j++)
	{
		std::cout << e->data << ' ';
		e = e->prev;
	}
	std::cout << std::endl << std::endl;
}

template< typename T>
MatrixElement<T>* search_first_element(MatrixElement_List<T>(&horizontal)[order], T data, int k) //ïîèñê ïåðâîãî ýëåìåíòà ïî çàäàííîìó óñëîâèþ
{
	MatrixElement<T>* e = new MatrixElement<T>;

	for (int i = 0; i < order; i++)
	{
		e = horizontal[i].end;
		while (e)
		{
			if (k == 1 && e->data > data)
				return e;
			if (k == 2 && ((int)e->data % (int)data) == 0)
				return e;
			e = e->prev;
		}

	}
	out_RED("Çíà÷åíèå íå íàéäåíî!");
	e = new MatrixElement<T>;
	return e;
}

template< typename T>
void search_first_element_by_condition(MatrixElement_List<T>(&horizontal)[order]) //ïîèñê ïåðâîãî ýëåìåíòà ïî çàäàííîìó óñëîâèþ
{
	out_BLUE_RED("Ïîèñê ïåðâîãî ýëåìåíòà ïî çàäàííîìó óñëîâèþ");
	std::cout << "i = ";

	std::cout << "Âûáåðåòå óñëîâèå: " << std::endl
		<< "1 - ïåðâûé ýëåìåíò, êîòîðûé > çàäàíîãî ÷èñëà" << std::endl
		<< "2 - ïåðâûé ýëåìåíò, êîòîðûé äåëèòüñÿ íàöåëî íà çàäàíîå ÷èñëî" << std::endl;
	int k;
	std::cin >> k;
	std::cout << "Çàäàéòå ÷èñëî: " << std::endl;
	double data;
	std::cin >> data;

	MatrixElement<T>* e = new MatrixElement<T>;
	if (data == 0 && k == 2)
		out_RED("Äåëèòü íà 0 íåëüçÿ!");
	else
		e = search_first_element(horizontal, data, k);
	std::cout << "e->i = " << e->i << '	' << "e->j = " << e->j << '	' << "e->data = " << e->data << std::endl;
}
