import pandas as pd
import glob
import os


def test():
    import pandas as pd
    df = pd.read_csv(r'archive\(15).csv')
    df.to_json(r'testFile.json', orient='records', lines=True)


def prod():
    joined_files = os.path.join("archive", "*.csv")

    joined_list = glob.glob(joined_files)

    df = pd.concat(map(pd.read_csv, joined_list), ignore_index=True)
    df.to_json(r'data.json', orient='records', lines=True)


if __name__ == '__main__':
    test()
    # prod()
