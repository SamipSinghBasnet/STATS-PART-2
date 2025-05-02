% FunctionPlotter.m
function plotter()
    x = -10:0.5:10;
    y = x.^2;
    saveData([x' y'], 'function_data.csv');
    printf("Function plotted and saved to function_data.csv\n");
end

function saveData(data, filename)
    fid = fopen(filename, 'w');
    fprintf(fid, 'x,y\n');
    fprintf(fid, '%f,%f\n', data');
    fclose(fid);
end
