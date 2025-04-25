import os
from collections import OrderedDict

import pandas as pd
import matplotlib.pyplot as plt
import mplcursors
import numpy as np
import math

csv_directory = 'D:\\Projects\\jdbc_mapper\\cycleLog'
read_col = 'timeInNano'
list_size = 'listSize'


def print_average(dict_by_ele_size):
    _dict = {}
    for k, v in dict_by_ele_size.items():
        print(f"Average time to create list of size {k} is {np.average(v)} in {read_col}")
        _dict[k] = math.floor(np.average(v))
    return _dict


def get_data():
    csv_dataframe = []
    size_element = []
    res = {}

    for filename in os.listdir(csv_directory):
        if filename.endswith('.csv'):
            file_path = os.path.join(csv_directory, filename)

            data = pd.read_csv(file_path, usecols=[list_size, read_col])
            for i in data[list_size]:
                size_element.append(f"{i}")

            csv_dataframe.append(data)

    size_element = set(size_element)
    for size in size_element:
        res.setdefault(size, [])
        for data in csv_dataframe:
            filtered_names = data.loc[data[list_size] == int(size), [read_col]].values.tolist()
            res[size].append(filtered_names[0][0])
    return res, size_element


combined_data, list_elements = get_data()
average_size_dict = print_average(combined_data)

plt.bar(average_size_dict.keys(), average_size_dict.values(), label='Data')

plt.xlabel('Total List Size')
plt.ylabel('Time In Nanosecond')
plt.title('Line Chart from Multiple CSV Files')
mplcursors.cursor(hover=True)
#plt.xticks(list_elements)
plt.legend()
plt.show()
