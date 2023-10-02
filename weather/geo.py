import csv
import requests

# 读取文件
input_file = 'stations.csv'  # 请替换为实际文件路径
output_file = 'output_file.csv'  # 输出文件

# 高德地图API Key
api_key = 'ef1273692437928435074aaeb1caa9d5'  # 请替换为实际的API Key

with open(input_file, mode='r') as infile, open(output_file, mode='w', newline='') as outfile:
    # 初始化读取和写入对象
    reader = csv.DictReader(infile)
    fieldnames = reader.fieldnames + ['FORMATTED_ADDRESS']
    writer = csv.DictWriter(outfile, fieldnames=fieldnames)
    
    # 写入表头
    writer.writeheader()
    
    # 遍历文件的每一行
    for row in reader:
        # 获取经纬度
        longitude = row['LONGITUDE']
        latitude = row['LATITUDE']
        
        # 构造URL
        url = f"https://restapi.amap.com/v3/geocode/regeo?output=JSON&location={longitude},{latitude}&key={api_key}&extensions=base"
        
        # 发送HTTP请求
        response = requests.get(url)
        
        # 判断响应状态码是否为200，即请求成功
        if response.status_code == 200:
            # 在当前行追加返回值
           if response.status_code == 200:
            try:
                data = response.json()
                address_component = data.get('regeocode', {}).get('addressComponent', {})
                
                # 拼接地址
                country = address_component.get('country', '')
                province = address_component.get('province', '')
                city = address_component.get('city', '')
                district = address_component.get('district', '')
                
                formatted_address = f"{country}{province}{city}{district}"
                
                # 在当前行追加地址
                row['FORMATTED_ADDRESS'] = formatted_address
            except Exception as e:
                row['FORMATTED_ADDRESS'] = 'Error: ' + str(e)
        else:
            row['FORMATTED_ADDRESS'] = 'Error: ' + str(response.status_code)

        
        # 写入新的csv文件
        writer.writerow(row)
