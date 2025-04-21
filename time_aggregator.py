import pandas as pd
from pathlib import Path

# Define the directory containing the CSV files
directory = Path('D:\\Projects\\jdbc_mapper\\cycleLog')

# Get a list of all CSV files in the directory
csv_files = list(directory.glob('*.csv'))

# Read each CSV file and concatenate them into a single DataFrame
dataframes = [pd.read_csv(file) for file in csv_files]
combined_df = pd.concat(dataframes, ignore_index=True)

print(combined_df)
