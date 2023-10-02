import os
import csv
import gzip
import shutil
from ftplib import FTP

def read_station_ids(file_path):
    with open(file_path, 'r') as f:
        reader = csv.reader(f)
        next(reader)  # 跳过表头
        station_ids = [row[0] for row in reader]
        # 遍历 station_ids，去掉字符串末尾的99999
        for i in range(len(station_ids)):
            station_ids[i] = station_ids[i].rstrip('99999')
    return station_ids

def download_isd_lite_data(ftp, station_id, year, output_dir, decompressed_file_path):
    file_name = f'{station_id}-99999-{year}.gz'
    empty_file_name = f'{station_id}-99999-{year}_empty.gz'

    remote_path = f'/pub/data/noaa/isd-lite/{year}/{file_name}'

    # 本地文件路径 = output_dir + year + file_name
    output_dir_year = os.path.join(output_dir, str(year))
    local_path = os.path.join(output_dir_year, file_name)
    empty_local_path = os.path.join(output_dir_year, empty_file_name)

    if not os.path.exists(output_dir_year):
        os.makedirs(output_dir_year)

    if not os.path.exists(output_dir_year):
        os.makedirs(output_dir_year)
    
    # 本地文件已存在，跳过
    if os.path.exists(local_path):
        print(f'{local_path} already exists. Skipping...')
        return
    # 存在占位文件，表示查询过了，不存在，直接跳过
    if os.path.exists(empty_local_path):
        print(f'{empty_local_path} exists, file empty. Skipping...')
        return

    try:
        ftp.cwd(os.path.dirname(remote_path))  # 尝试进入文件所在目录
        if file_name in ftp.nlst():  # 检查文件是否在目录列表中
            # 在本地创建一个与FTP上相同的文件
            with open(local_path, 'wb') as local_file:
                print(f'Downloading {remote_path}...')
                ftp.retrbinary('RETR ' + file_name, local_file.write)
                print(f'{remote_path} Downloaded successfully.')
                # 解压缩文件 到 decompressed_file_path/文件名

                with gzip.open(local_path, 'rb') as gz_file:
                    with open(os.path.join(decompressed_file_path, file_name.rstrip('.gz')), 'wb') as decompressed_file:
                        shutil.copyfileobj(gz_file, decompressed_file)
                print(f'{local_path} has been decompressed to {decompressed_file_path}')
        else:
            # 文件不存在，创建一个空文件
            with open(empty_local_path, 'w') as local_file:
                print(f'{remote_path} does not exist. Skipping...')
    except Exception as e:  # 捕获权限错误，例如文件不存在
            print(f'Error accessing {remote_path}: {e}. Skipping...')

def download_china_isd_lite_data(stations_file, start_year, end_year, output_dir):
    ftp = FTP('ftp.ncdc.noaa.gov')
    ftp.login()

    # 配置解压缩地址
    decompressed_file_path = os.path.join(output_dir, 'source')
    if not os.path.exists(decompressed_file_path):
        os.makedirs(decompressed_file_path)

    station_ids = read_station_ids(stations_file)

    for station_id in station_ids:
        for year in range(start_year, end_year + 1):
            download_isd_lite_data(ftp, station_id, year, output_dir, decompressed_file_path)

    ftp.quit()

if __name__ == '__main__':
    stations_file = 'output_file.csv'   ###国内站点数据，请查看附件。
    start_year = 1948                        ###时间范围
    end_year = 2022
    output_dir = 'Documents'   ####我的保存文件路径，需要修改
    
    if not os.path.exists(output_dir):
        os.makedirs(output_dir)

    download_china_isd_lite_data(stations_file, start_year, end_year, output_dir)