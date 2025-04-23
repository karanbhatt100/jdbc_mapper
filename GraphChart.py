import os
import pandas as pd
import matplotlib.pyplot as plt
import mplcursors
import numpy as np

csv_directory = 'D:\\Projects\\jdbc_mapper\\cycleLog'
read_col = 'timeInNano'

def array_ele(ele_array, dict_by_ele_size):
    for i in ele_array:
        if i['listSize'][0] in dict_by_ele_size:
            dict_by_ele_size[i['listSize'][0]].append(i[read_col][0])
        else:
            dict_by_ele_size[i['listSize'][0]] = [i[read_col][0]]
    return

def print_average(dict_by_ele_size):
    for k,v in dict_by_ele_size.items():
        print(f"Average time to create list of size {k} is {np.average(v)}")
    return

def get_data():
    list_elements = []
    all_data = []
    dict_by_ele_size = {}

    for filename in os.listdir(csv_directory):
        if filename.endswith('.csv'):
            file_path = os.path.join(csv_directory, filename)

            data = pd.read_csv(file_path,usecols=['listSize', read_col])

            for i in data['listSize']:
                list_elements.append(f"{i}")

            all_data.append(data[['listSize', read_col]])
            array_ele(all_data, dict_by_ele_size)

    print_average(dict_by_ele_size)
    return all_data, list_elements

all_data, list_elements = get_data()
combined_data = pd.concat(all_data, ignore_index=False)

plt.bar(list_elements, combined_data['timeInNano'], label='Data')

plt.xlabel('Total List Size')
plt.ylabel('Time In Nanosecond')
plt.title('Line Chart from Multiple CSV Files')
plt.xticks(list_elements)
mplcursors.cursor(hover=True)
plt.legend()
plt.show()
