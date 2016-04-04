/*
 * Copyright (C) 2016 fernando.caballero
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package injector;

import consumer.FileConsumer;
import consumer.FileProcessingConsumer;
import service.FileProcessingService;

/**
 *
 * Implementation of the ServiceInjector interface.
 * 
 * @author fernando.caballero
 */
public class FileServiceInjector implements ServiceInjector{
    
    /**
     * 
     * Method that returns instance of the consumer. It creates the consumer object
     * using its constructor and passing to it an instance of the Service as parameter.
     * In the consumer constructor the service parameter will be injected.
     * 
     * @return FileConsumer object
     */
    @Override
    public FileConsumer getConsumer() {
        return new FileProcessingConsumer(new FileProcessingService());
    }
    
}
