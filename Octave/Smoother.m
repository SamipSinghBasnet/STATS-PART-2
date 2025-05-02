% Smoother.m
function Smoother()
    windowSize = 5;
    span = 2 * windowSize + 1;

    % Process salted_data1.csv
    data1 = loadData('salted_data1.csv');
    x = data1(:,1);
    y1 = data1(:,2);
    smoothed1 = movmean(y1, span);
    saveData([x smoothed1], 'smoothed_data1.csv');
    printf("Data smoothed and saved to smoothed_data1.csv\n");

    % Process salted_data2.csv
    data2 = loadData('salted_data2.csv');
    y2 = data2(:,2);
    smoothed2 = movmean(y2, span);
    saveData([x smoothed2], 'smoothed_data2.csv');
    printf("Data smoothed and saved to smoothed_data2.csv\n");

    % Process salted_data3.csv
    data3 = loadData('salted_data3.csv');
    y3 = data3(:,2);
    smoothed3 = movmean(y3, span);
    saveData([x smoothed3], 'smoothed_data3.csv');
    printf("Data smoothed and saved to smoothed_data3.csv\n");
end

function data = loadData(filename)
    data = dlmread(filename, ',', 1, 0);  % Skip header row
end

function saveData(data, filename)
    fid = fopen(filename, 'w');
    fprintf(fid, 'x,y\n');
    fprintf(fid, '%f,%f\n', data');
    fclose(fid);
end
