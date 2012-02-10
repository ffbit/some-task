
Test task for [Yandex](http://yandex.ua)
=======================================

About
-----

Develop an application to parse XML files and calculate number of **item** elements having count field value greater than *7*. Download the file from the specified URL. Write result into the file, database and send to the specified email.

File path, email address and database connection parameters should be specified via command line.

Your application should be a single jar without extra dependencies that can be run using `java -jar` command.

The XML file size can be counted in gigabytes.

Sample XML:

    <root>
        <records>
            <item count="8"/>
            <item count="4"/>
            <notItem count="12"/>
            <item count="11"/>
        </records>
    </root>


MySQL requirements:
-------------------
    
    CREATE TABLE `reports`(
        `id` INT AUTO_INCREMENT PRIMARY KEY,
        `quantity` BIGINT NOT NULL,
        `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
    );

Usage
-----

    java -jar yandex-task-0.0.1.jar \
        --url url_to_xml_file       \
        --output-file output_file   \
        --email your@email.com      \
        --db-type mysql             \
        --db-host localhost         \
        --db-port 3306              \
        --db-schema dbname          \
        --db-user user              \
        --db-password pass

Yandex, thank you for the great test task.
------------------------------------------
