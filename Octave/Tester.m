% Tester.m
function Tester()
    % Step 1: Plot the function
    plotter();    % Changed from plotter() to match the file name

    % Step 2: Salt the data
    Salter();

    % Step 3: Smooth the data
    Smoother();

    % Plot all results
    plotResults();
end

function plotResults()
    % Load all data files

        orig = dlmread('function_data.csv', ',', 1, 0);
        salt1 = dlmread('salted_data1.csv', ',', 1, 0);
        salt2 = dlmread('salted_data2.csv', ',', 1, 0);
        salt3 = dlmread('salted_data3.csv', ',', 1, 0);
        smooth1 = dlmread('smoothed_data1.csv', ',', 1, 0);
        smooth2 = dlmread('smoothed_data2.csv', ',', 1, 0);
        smooth3 = dlmread('smoothed_data3.csv', ',', 1, 0);

        % Create plot
        figure;
        plot(orig(:,1), orig(:,2), 'b-', 'LineWidth', 2, 'DisplayName', 'Original');
        hold on;
        plot(salt1(:,1), salt1(:,2), 'r.', 'DisplayName', 'Salted (range=1.0)');
        plot(salt2(:,1), salt2(:,2), 'g.', 'DisplayName', 'Salted (range=2.0)');
        plot(salt3(:,1), salt3(:,2), 'm.', 'DisplayName', 'Salted (range=3.0)');
        plot(smooth1(:,1), smooth1(:,2), 'r-', 'LineWidth', 2, 'DisplayName', 'Smooth 1.0');
        plot(smooth2(:,1), smooth2(:,2), 'g-', 'LineWidth', 1, 'DisplayName', 'Smooth 2.0');
        plot(smooth3(:,1), smooth3(:,2), 'm-', 'LineWidth', 1, 'DisplayName', 'Smooth 3.0');
        legend('show');
        grid on;
        title('Function Plot with Different Salting Ranges and Smoothing');
        xlabel('x');
        ylabel('y');


end
