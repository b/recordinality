package com.cscotta.recordinality;

import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Random;

public class HashingWrapperFactory
{
    public static HashingWrapper newHashingWrapper(Class klass)
    {
        if (klass == String.class)
        {
            return new StringHashingWrapper();
        }

        if (klass == Integer.class)
        {
            return new IntegerHashingWrapper();
        }

        if (klass == Long.class)
        {
            return new LongHashingWrapper();
        }

        throw new ClassCastException();
    }

    public static class HashingWrapper
    {
        protected final int seed = new Random().nextInt();
        protected final HashFunction hash = Hashing.murmur3_128(seed);

        public HashCode hash(Object o)
        {
            throw new NotImplementedException();
        }
    }

    private static class StringHashingWrapper extends HashingWrapper
    {
        public StringHashingWrapper()
        {
            super();
        }

        @Override
        public HashCode hash(Object input)
        {
            return hash.hashString((String)input);
        }
    }

    private static class IntegerHashingWrapper extends HashingWrapper
    {
        public IntegerHashingWrapper()
        {
            super();
        }

        @Override
        public HashCode hash(Object input)
        {
            return hash.hashInt((Integer)input);
        }
    }

    private static class LongHashingWrapper extends HashingWrapper
    {
        public LongHashingWrapper()
        {
            super();
        }

        @Override
        public HashCode hash(Object input)
        {
            return hash.hashLong((Long)input);
        }
    }
}
